package com.example.demo.controller;

import com.example.demo.message.JwtResponse;
import com.example.demo.message.LoginForm;
import com.example.demo.message.ResponseMessage;
import com.example.demo.message.SignUpForm;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.services.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        String username = this.decodeCredential(loginRequest.getUsername());
        String password = this.decodeCredential(loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();


        return ResponseEntity.ok(new JwtResponse(userPrincipal.getId(), userPrincipal.getUsername(), userPrincipal.getFirstName(), userPrincipal.getLastName(),
                   userPrincipal.getEmail(), jwt, userPrincipal.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getBirthDate(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        for (String role: strRoles
             ) {
            System.out.println(role);
        }
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    try{
                        Role adminRole = roleRepository.findByRolename("ROLE_ADMIN");
                        roles.add(adminRole);
                    }
                    catch (RuntimeException e){
                        System.out.println("Fail! -> Cause: User Role not find.");
                    }
                    break;
                case "pm":
                    try{
                        Role pmRole = roleRepository.findByRolename("ROLE_PM");
                        roles.add(pmRole);
                    }catch (RuntimeException e){
                        System.out.println("Fail! -> Cause: User Role not find.");
                    }
                    break;
                default:
                    try{
                        Role userRole = roleRepository.findByRolename("ROLE_USER");
                        roles.add(userRole);
                    }
                    catch (RuntimeException e){
                        System.out.println("Fail! -> Cause: User Role not find.");
                    }
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

    private String decodeCredential(String credential) {
        byte[] decode = Base64.getDecoder().decode(credential);
        return new String(decode);
    }

}
