package com.introtuce.introtuce.controllers.status;

import com.introtuce.introtuce.services.jwt.MyUserDetailsService;
import com.introtuce.introtuce.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class StatusController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;


    @GetMapping("get-token")
    public ResponseEntity<?> getToken() throws Exception {
        Map<String,String> token = new HashMap<>();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken("sai@gmail.com","123456")
            );
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername("sai@gmail.com");
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            token.put("token",jwt);
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        return ResponseEntity.ok(token);
    }

}
