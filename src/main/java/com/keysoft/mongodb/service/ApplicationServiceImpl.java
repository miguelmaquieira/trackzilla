package com.keysoft.mongodb.service;

import com.keysoft.mongodb.model.Application;
import com.keysoft.mongodb.model.Ticket;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final MongoTemplate mongoTemplate;

    public ApplicationServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void addNewApplicationWTemplate(Application application) {
        this.mongoTemplate.insert(application);
    }

    @Override
    public Application findByIdTemplate(String id) {
        return mongoTemplate.findById(id, Application.class);
    }

    @Override
    public void deleteWTemplate(Application application) {
        mongoTemplate.remove(application);
    }

    @Override
    public void updateApplicationWTemplate(Application application) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(application.getName()));
        Update update = new Update();
        update.set("name", "Trainer");
        mongoTemplate.updateFirst(query, update, Application.class);
    }

    @Override
    @Transactional
    public void retireApplication(Application application) {
        //Step 1
        mongoTemplate.remove(application);

        //Step 2
        Query query = new Query();
        query.addCriteria(Criteria.where("appId").is(application.getId()));
        Update update = new Update();
        update.set("status", "Cancel");
        mongoTemplate.updateMulti(query, update, Ticket.class);
    }
}
