package com.introtuce.introtuce.controllers.user;


import com.introtuce.introtuce.dao.user.AuthRequestBody;
import com.introtuce.introtuce.dao.user.AuthenticationBody;
import com.introtuce.introtuce.entity.user.User;
import com.introtuce.introtuce.repository.user.UserRepository;
import com.introtuce.introtuce.services.jwt.MyUserDetailsService;
import com.introtuce.introtuce.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequestBody r){

        System.out.println("###########################");
        System.out.println(r.getEmail()+"  "+r.getUsername()+"  "+r.getPassword());
        System.out.println("###########################");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(r.getPassword());

        User check= userRepository.findByEmail(r.getEmail());

        if( check == null || !check.getEmail().equals(r.getEmail())){
            userRepository.save(
                    new User(userRepository.count()+1,r.getUsername(),password,r.getEmail())
            );
        }else{
            return ResponseEntity.badRequest().body("already exists");
        }

        Map msg=new HashMap();
        msg.put("msg","Success");

        return ResponseEntity.ok(msg);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationBody r) throws Exception
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken((String) r.getEmail(),(String) r.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername((String) r.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        Map res = new HashMap();
        res.put("token",jwt);
        return ResponseEntity.ok(res);
    }

}
