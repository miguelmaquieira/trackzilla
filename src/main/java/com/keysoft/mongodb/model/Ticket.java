package com.keysoft.mongodb.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor
public class Ticket {

    @Id
    private String id;
    private final String title;
    private final String description;
    @Indexed(name = "appId_index", direction = IndexDirection.ASCENDING)
    private final String appId;
    private final String status;
}
