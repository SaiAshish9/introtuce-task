package com.introtuce.introtuce.repository.user.status;

import com.introtuce.introtuce.entity.user.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentsRepository extends MongoRepository<Comment,Integer> {

}
