/*
package com.agri.learnings.serviceimpl;

import com.agri.learnings.constants.CommonConstants;
import com.agri.learnings.entity.Role;
import com.agri.learnings.entity.Users;
import com.agri.learnings.exception.UserPersistException;
import com.agri.learnings.repo.RoleRepo;
import com.agri.learnings.repo.UserRepo;
import com.agri.learnings.request.CommonRequest;
import com.agri.learnings.response.CommonResponse;
import com.agri.learnings.service.JwtService;
import com.agri.learnings.service.UserService;
import com.agri.learnings.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepo userRepo;
    RoleRepo roleRepo;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @Override
    public ResponseEntity<Object> saveUserDetails(CommonRequest commonRequest) {
        log.info("Incoming Request to Save User Details : {}", commonRequest);
        try {
            String roleName = CommonConstants.ROLE + "USER";
            Role userRole = roleRepo.findByRole(roleName)
                    .orElseGet(() -> roleRepo.save(new Role(roleName)));

            Users users = Users.builder()
                    .email(commonRequest.getEmail())
                    .contactNumber(commonRequest.getContactNumber())
                    .username(commonRequest.getUsername())
                    .password(passwordEncoder.encode(commonRequest.getPassword()))
                    .isActive(true)
                    .roles(Collections.singleton(userRole))
                    .createdDate(DateUtils.createdDate())
                    .build();

            Users savedUser = userRepo.save(users);
            log.info("User Saved Successfully with ID: {}", savedUser.getId());

            CommonResponse commonResponse = CommonResponse.builder()
                    .responseCode(String.valueOf(HttpStatus.OK.value()))
                    .responseMessage("Registration Success, Please use your credentials to login")
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        } catch (Exception e) {
            log.error("Exception Occurred while trying to save user details: {}", e.getMessage());
            String responseCode = e instanceof UserPersistException
                    ? String.valueOf(HttpStatus.BAD_REQUEST.value())
                    : String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());

            throw new UserPersistException(responseCode, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> login(CommonRequest commonRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(commonRequest.getUsername(), commonRequest.getPassword()));
        return authentication.isAuthenticated() ? ResponseEntity.status(HttpStatus.OK).body(jwtService.generateToken(commonRequest.getUsername()))
                : ResponseEntity.status(HttpStatus.EXPECTATION_FAILED.value()).body(new CommonResponse("404", "Login Failed", ""));
    }
}
*/
