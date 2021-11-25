package com.ihc.ppgcbackend.service;

import com.ihc.ppgcbackend.model.Presence;
import com.ihc.ppgcbackend.model.Status;
import com.ihc.ppgcbackend.repository.PresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PresenceService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PresenceRepository presenceRepo;

    public Presence addPresence(int day, int month, int year, String status, String reason, String userId) {
        Presence newPresence = new Presence(day, month, year, Status.valueOf(status), reason, userId);
        return presenceRepo.insert(newPresence);
    }

    public String addReason(String id, String reason) {

        Optional<Presence> pOpt = presenceRepo.findById(id);
        if(pOpt.isPresent()) {
            Presence presence = pOpt.get();
            if(presence.getStatus() == Status.JUSTIFIED)
                return "Already justified";
            else if(presence.getStatus() == Status.PRESENT)
                return "User present that day.";
            else if(presence.getStatus() == Status.ABSENT) {
                presence.setStatus(Status.JUSTIFIED);
                presence.setReason(reason);
                presenceRepo.save(presence);
                return "Reason saved successfully";
            }
        } else {
            return "Presence not found for this day.";
        }
        return "Generic error";
    }

    public List<Presence> getUserMonthStatus(String userId, int month, int year) {
        Query query = new Query();
        query.addCriteria( Criteria.where("month").is(month).and("year").is(year).and("userId").is(userId) );
        return mongoTemplate.find(query, Presence.class);
    }

}