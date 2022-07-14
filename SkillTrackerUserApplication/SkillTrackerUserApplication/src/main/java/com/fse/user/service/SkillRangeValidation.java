package com.fse.user.service;

import java.util.List;

import com.fse.user.exception.InvalidSkillRangeException;
import com.fse.user.model.NonTechSkill;
import com.fse.user.model.TechnicalSkill;

public class SkillRangeValidation {
	
	public static void techSkillRangeValidate(List<TechnicalSkill> techSkill) {
		
		techSkill.forEach((skill)->{
			if(skill.getTechSkillRange().isEmpty() || skill.getTechSkillRange() == null || skill.getTechSkillRange().isBlank()) {
				throw new InvalidSkillRangeException(skill.getTechSkillName(),"Skill level cant be empty or null");
			}
			if(Integer.valueOf(skill.getTechSkillRange()) < 0 || Integer.valueOf(skill.getTechSkillRange()) > 20) {
				throw new InvalidSkillRangeException(skill.getTechSkillName(),"Skill level should be in the 10 to 20 range");
			}
		});
	}
		
	public static void NonTechSkillRangeValidate(List<NonTechSkill> nonTechSkill) {
			
		nonTechSkill.forEach((skill)->{
			if(skill.getNonTechSkillRange() .isEmpty() || skill.getNonTechSkillRange() == null || skill.getNonTechSkillRange().isBlank()) {
				throw new InvalidSkillRangeException(skill.getNonTechSkillName(),"Skill level cant be empty or null");
			}
			if(Integer.valueOf(skill.getNonTechSkillRange()) < 0 || Integer.valueOf(skill.getNonTechSkillRange()) > 20) {
				throw new InvalidSkillRangeException(skill.getNonTechSkillName(),"Skill level should be in the 10 to 20 range");
			}
		});	
	}

}
