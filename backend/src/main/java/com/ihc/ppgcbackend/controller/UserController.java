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
    public Map<String, Object> newUser(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("advisor") String advisor) throws IOException {
        User user = userService.createUser(name, email, phone, password, advisor);
        Map<String, Object> rtn = new LinkedHashMap<>();
        rtn.put("status", HttpStatus.OK);
        rtn.put("userId", user.getId());
        return rtn;
    }

    @RequestMapping(value ="/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> authenticateUser(@RequestParam("user") String name, @RequestParam("password") String password) throws IOException {
        Map<String, Object> rtn = userService.authenticate(name, password);
        return rtn;
    }

    @RequestMapping(value ="/get", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> getUser(@RequestParam("userId") String userId) throws IOException {
        Map<String, Object> rtn = userService.getUser(userId);
        if(rtn.isEmpty())
            rtn.put("status", HttpStatus.NOT_FOUND);
        else {
            rtn.put("status", HttpStatus.OK);
        }
        return rtn;
    }


}
