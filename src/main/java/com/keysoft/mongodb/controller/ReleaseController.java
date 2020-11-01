package com.keysoft.mongodb.controller;

import com.keysoft.mongodb.model.Release;
import com.keysoft.mongodb.service.ReleaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/tza/releases")
public class ReleaseController {

    private final ReleaseService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Release> getAllReleases() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Release> getReleaseId(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Release addNewRelease(@RequestBody Release release){
        return service.save(release);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Release updateRelease(@PathVariable("id") String id, @RequestBody Release release){
        release.setId(id);
        return service.save(release);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRelease(@PathVariable("id") String id) {
        service.deleteById(id);
    }

    @RequestMapping(value ="/tickets", method = RequestMethod.PUT)
    public void addNewReleaseWTickets(@RequestBody Release release) {
        service.insert(release);
    }

    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public List<Release> getReleaseByTicketStatus(@PathVariable("status") String status){
        return service.getReleaseByTicketStatus(status);
    }

    @RequestMapping(value = "/costs/{id}", method = RequestMethod.GET)
    public Double getReleaseCost(@PathVariable("id") String id) {
        return service.getCosts(id);
    }
}
