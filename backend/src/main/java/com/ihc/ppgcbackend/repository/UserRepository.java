package com.ihc.ppgcbackend.repository;

import com.ihc.ppgcbackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
