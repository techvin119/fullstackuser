package com.fse.user.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.user.exception.InvalidAssociateId;
import com.fse.user.exception.InvalidUserIdExcpetion;
import com.fse.user.exception.UpdateProfileDaysException;
import com.fse.user.model.Profiles;
import com.fse.user.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SequenceGeneratorService sequenceGenerator;
	
	public Profiles addProfile(Profiles profile) {
		
		if(!profile.getAssociateId().startsWith("CTS"))
		{
			throw new InvalidAssociateId(profile.getAssociateId(),"The Associate Id should begin with CTS");
		}
		
		SkillRangeValidation.techSkillRangeValidate(profile.getTechSkillList());
		SkillRangeValidation.NonTechSkillRangeValidate(profile.getNonTechSkillList());			
		profile.setId(sequenceGenerator.getSequenceNumber(Profiles.SEQUENCE_NAME));
		profile.setCreateOrLastUpdate(LocalDate.now());
		return this.userRepository.save(profile);
	}
	
	public String updateProfile(Profiles profile, int id) {
		
		Profiles dbProfile = userRepository.findById(id).orElseThrow(()-> new InvalidUserIdExcpetion(id));
		
		profile.setCreateOrLastUpdate(LocalDate.now());
		long daysDiff = ChronoUnit.DAYS.between(dbProfile.getCreateOrLastUpdate(),profile.getCreateOrLastUpdate());
		if(daysDiff <= 10) {
			throw new UpdateProfileDaysException();
		}		
				
		SkillRangeValidation.techSkillRangeValidate(profile.getTechSkillList());
		SkillRangeValidation.NonTechSkillRangeValidate(profile.getNonTechSkillList());
		
		dbProfile.setTechSkillList(profile.getTechSkillList());
		dbProfile.setNonTechSkillList(profile.getNonTechSkillList());
		dbProfile.setCreateOrLastUpdate(profile.getCreateOrLastUpdate());
			
		userRepository.save(dbProfile);
		return "Updated the UserId " + id + " Sucessfully" ;

	}
	
}


