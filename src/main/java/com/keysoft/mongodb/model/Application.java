package com.keysoft.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Application {

    @Id
    private String id;
    private String name;
    private String description;
    private String owner;
}
