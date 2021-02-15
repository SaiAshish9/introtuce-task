package com.introtuce.introtuce.entity.user;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    private String username;

    private String password;

    @Indexed(unique=true)
    private String email;

}