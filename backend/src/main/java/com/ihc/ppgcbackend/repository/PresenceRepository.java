package com.ihc.ppgcbackend.repository;

import com.ihc.ppgcbackend.model.Presence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PresenceRepository extends MongoRepository<Presence, String> {
}
