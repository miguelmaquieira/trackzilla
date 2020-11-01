package com.keysoft.mongodb.controller;

import com.keysoft.mongodb.model.Application;
import com.keysoft.mongodb.repositories.ApplicationRepository;
import com.keysoft.mongodb.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/tza/applications")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;
    private final ApplicationService applicationService;

    // CRUD operations
    @RequestMapping(method = RequestMethod.GET)
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Application> getApplicationById(@PathVariable("id") String id) {
        return applicationRepository.findById(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public List<Application> findByName(@PathVariable("name") String name) {
        return applicationRepository.findByName(name);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Application addNewApplication(@RequestBody Application application){
        return applicationRepository.save(application);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Application updateApplication(@PathVariable("id") String id, @RequestBody Application application){
        application.setId(id);
        return applicationRepository.save(application);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteApplication(@PathVariable("id") String id) {
        applicationRepository.deleteById(id);
    }

    // Template operations
    @RequestMapping(value = "/template", method = RequestMethod.POST)
    public void addNewApplicationWTemplate(@RequestBody Application application){
        applicationService.addNewApplicationWTemplate(application);
    }

    @RequestMapping(value = "/template/name/{id}", method = RequestMethod.GET)
    public Application findByIdTemplate(@PathVariable("id") String id){
        return applicationService.findByIdTemplate(id);
    }

    @RequestMapping(value = "/template", method = RequestMethod.DELETE)
    public void deleteWTemplate(@RequestBody Application application){
        applicationService.deleteWTemplate(application);
    }

    @RequestMapping(value = "/template", method = RequestMethod.PUT)
    public void updateApplicationWTemplate(@RequestBody Application application){
        applicationService.updateApplicationWTemplate(application);
    }

    @RequestMapping(value = "/retire", method = RequestMethod.DELETE)
    public void retireApplication(@RequestBody Application application) {  //just pass in the Id
        applicationService.retireApplication(application);
    }
}
