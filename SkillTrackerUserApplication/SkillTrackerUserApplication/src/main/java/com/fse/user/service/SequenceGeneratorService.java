package com.fse.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import com.fse.user.model.DbSequence;

@Service
public class SequenceGeneratorService {
	
	@Autowired
	private MongoOperations mongoOpertaions;

	public int getSequenceNumber(String sequenceName) {
		
		Query query = new Query(Criteria.where("_id").is(sequenceName));
		Update update = new Update().inc("seq",1);
		
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true).upsert(true);
		
		DbSequence counter = 
			mongoOpertaions.findAndModify(query, update, options, DbSequence.class);
		
			return !Objects.isNull(counter)? counter.getSeq() : 1;
			
		}
	
}
	

