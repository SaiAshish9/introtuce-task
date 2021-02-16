package com.introtuce.introtuce.repository.user;

import com.introtuce.introtuce.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,Long> {

    User findByEmail(String email);

    Optional<User> findById(Long id);

}
