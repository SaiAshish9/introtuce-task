package com.introtuce.introtuce.repository.user.status;

import com.introtuce.introtuce.entity.user.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatusRepository extends MongoRepository<Status,Integer> {

}
