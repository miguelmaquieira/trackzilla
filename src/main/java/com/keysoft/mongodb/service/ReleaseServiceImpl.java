package com.keysoft.mongodb.service;

import com.keysoft.mongodb.model.Release;
import com.keysoft.mongodb.repositories.ReleaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final MongoTemplate mongoTemplate;
    private final ReleaseRepository repository;

    @Override
    public List<Release> getReleaseByTicketStatus(String status) {
        Query query = new Query();

        //embedded document use tickets.status
        query.addCriteria(Criteria.where("tickets.status").is(status));

        //repository is not flexible enough to do this, so using mongoTemplate
        return mongoTemplate.find(query, Release.class);
    }

    @Override
    public List<Release> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Release> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Release save(Release release) {
        return repository.save(release);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void insert(Release release) {
        repository.insert(release);
    }

    @Override
    public Double getCosts(String id) {
        return findById(id).map(Release::getEstimatedCosts).orElse(0.0);
    }
}
