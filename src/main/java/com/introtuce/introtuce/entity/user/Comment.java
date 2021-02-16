package com.introtuce.introtuce.entity.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    private Integer id;

    private Integer userId;

    private Integer statusId;

    private String comment;

}
