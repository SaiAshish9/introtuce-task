package com.introtuce.introtuce.repository.user.status;

import com.introtuce.introtuce.entity.user.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StatusListRepository extends MongoRepository<Status,Integer> {

    Optional<Status> findByUserId(Long id);

    Optional<Status> findById(Integer id);

}
