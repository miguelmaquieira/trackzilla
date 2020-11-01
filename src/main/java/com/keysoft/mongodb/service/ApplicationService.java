package com.keysoft.mongodb.service;

import com.keysoft.mongodb.model.Application;

public interface ApplicationService {

    void addNewApplicationWTemplate(Application application);
    Application findByIdTemplate(String id);
    void deleteWTemplate(Application application);
    void updateApplicationWTemplate(Application application);
    void retireApplication(Application application);
}
