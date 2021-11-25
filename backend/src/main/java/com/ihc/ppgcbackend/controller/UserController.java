package com.ihc.ppgcbackend.controller;

import com.ihc.ppgcbackend.model.User;
import com.ihc.ppgcbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/new")
    public String addUser(Model model) {
        //used for testing
        return "createUser";
    }

    @RequestMapping(value ="/new", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> newUser(@RequestParam("name") String name, @RequestParam("e-mail") String email, @RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("advisor") String advisor) throws IOException {
        User user = userService.createUser(name, email, phone, password, advisor);
        Map<String, Object> rtn = new LinkedHashMap<>();
        rtn.put("status", HttpStatus.OK);
        rtn.put("userId", user.getId());
        return rtn;
    }

    @RequestMapping(value ="/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> authenticateUser(@RequestParam("user") String name, @RequestParam("password") String password) throws IOException {
        boolean exists = userService.authenticate(name, password);
        Map<String, Object> rtn = new LinkedHashMap<>();
        if(exists)
            rtn.put("status", HttpStatus.OK);
        else
            rtn.put("status", HttpStatus.NOT_FOUND);
        return rtn;
    }

    @RequestMapping(value ="/get", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> getUser(@RequestParam("userId") String userId) throws IOException {
        String message = userService.getUser(userId);
        Map<String, Object> rtn = new LinkedHashMap<>();
        if(message.equals("User not found."))
            rtn.put("status", HttpStatus.NOT_FOUND);
        else {
            rtn.put("status", HttpStatus.OK);
            rtn.put("userId", message);
        }
        return rtn;
    }


}