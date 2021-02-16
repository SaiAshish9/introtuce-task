package com.introtuce.introtuce.entity.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    private Integer id;

    private User user;

    private String description;

    private List<Comment> comments;

}
