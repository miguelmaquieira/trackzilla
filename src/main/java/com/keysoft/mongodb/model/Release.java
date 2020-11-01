package com.keysoft.mongodb.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;

@Document
@Data
@RequiredArgsConstructor
public class Release {

    @Id
    private String id;
    private final String name;
    private final String description;
    private final List<Ticket> tickets;
    private final ZonedDateTime releaseDate;

    @Transient
    private Double estimatedCosts;

    public Double getEstimatedCosts() {
        return tickets != null ? tickets.size() * 15.50 : 0;
    }
}
