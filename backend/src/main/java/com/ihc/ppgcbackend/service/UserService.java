package com.ihc.ppgcbackend.service;

import com.ihc.ppgcbackend.model.Presence;
import com.ihc.ppgcbackend.model.User;
import com.ihc.ppgcbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
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

    public Map<String, Object> authenticate(String name, String password) {
        Map<String, Object> rtn = new LinkedHashMap<>();
        Query query = new Query();
        query.addCriteria( Criteria.where("name").is(name).and("password").is(password));
        List<User> userOpt = mongoTemplate.find(query, User.class);
        if (userOpt.isEmpty()) {
            rtn.put("userId", "");
            rtn.put("status", HttpStatus.NOT_FOUND);
        }
        else {
            User user = userOpt.get(0);
            rtn.put("userId", user.getId());
            rtn.put("status", HttpStatus.OK);
        }
        return rtn;
    }
}
