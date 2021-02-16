package com.introtuce.introtuce.entity.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    private Integer id;

    private Long userId;

    private String description;

    private String mediaUrl;

    private List<Integer> comments;

}
