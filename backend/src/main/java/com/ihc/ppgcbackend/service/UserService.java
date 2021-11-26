package com.ihc.ppgcbackend.service;

import com.ihc.ppgcbackend.model.Presence;
import com.ihc.ppgcbackend.model.User;
import com.ihc.ppgcbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserRepository userRepo;

    public Map<String, Object> getUser(String userId) {
        Map<String, Object> rtn = new LinkedHashMap<>();
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            rtn.put("name", user.getName());
            rtn.put("email", user.getEmail());
            rtn.put("advisor", user.getAdvisor());
            rtn.put("phone", user.getPhone());
        }
        return rtn;
    }

    public User createUser(String name, String email, String phone, String password, String advisor) {
        User newUser = new User(name, email, phone, password, advisor);
        return userRepo.insert(newUser);
    }

    public boolean authenticate(String name, String password) {
        Query query = new Query();
        query.addCriteria( Criteria.where("name").is(name).and("password").is(password));
        if (mongoTemplate.find(query, Presence.class).isEmpty())
            return false;
        else
            return true;
    }
}
