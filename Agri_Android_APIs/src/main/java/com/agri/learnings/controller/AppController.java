package com.agri.learnings.controller;

import com.agri.learnings.request.CommonRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/open/v1.0")
public class AppController {


   /* @Autowired
    public AppController(UserService userService) {
        this.userService = userService;
    }*/

    @GetMapping("/greet")
    public String welcome(){
        return "Welcome to the cloud environment, Now you are accessing the API from EC2 using Jenkins and Docker";
    }

  /*  @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody CommonRequest commonRequest) {
        return userService.login(commonRequest);
    }*/

   /* @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody CommonRequest commonRequest) {
        return userService.saveUserDetails(commonRequest);
    }*/
}
