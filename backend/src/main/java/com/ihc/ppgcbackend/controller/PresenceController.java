package com.ihc.ppgcbackend.controller;

import com.ihc.ppgcbackend.model.Presence;
import com.ihc.ppgcbackend.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("presence")
public class PresenceController {

    @Autowired
    PresenceService presenceService;

    @GetMapping("/upload")
    public String uploadPresence(Model model) {
        //used for testing
        return "addPresence";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> addPresence(@RequestParam("day") int day, @RequestParam("month") int month, @RequestParam("year") int year, @RequestParam("userId") String userId, @RequestParam("status") String status, @RequestParam("reason") String reason) throws IOException {
        Presence presence = presenceService.addPresence(day, month, year, status, reason, userId);
        Map<String, Object> rtn = new LinkedHashMap<>();
        rtn.put("status", HttpStatus.OK);
        rtn.put("presenceId", presence.getId());
        return rtn;
    }

    @RequestMapping(value ="/justify", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> justifyAbsence(@RequestParam("presenceId") String id, @RequestParam("reason") String reason) throws IOException {
        String message = presenceService.addReason(id, reason);
        Map<String, Object> rtn = new LinkedHashMap<>();
        rtn.put("status", HttpStatus.OK);
        rtn.put("message", message);
        return rtn;
    }


    @RequestMapping(value ="/get", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> getUserMonthStatus(@RequestParam("userId") String userId, @RequestParam("month") int month, @RequestParam("year") int year ) throws IOException {
        List<Presence> presenceList = presenceService.getUserMonthStatus(userId, month, year);
        Map<String, Object> rtn = new LinkedHashMap<>();
        rtn.put("status", HttpStatus.OK);
        rtn.put("presences", presenceList);
        return rtn;
    }
}
