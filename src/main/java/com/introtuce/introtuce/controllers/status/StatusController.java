package com.introtuce.introtuce.controllers.status;

import com.introtuce.introtuce.dao.status.StatusBody;
import com.introtuce.introtuce.repository.user.status.CommentsRepository;
import com.introtuce.introtuce.repository.user.status.StatusListRepository;
import com.introtuce.introtuce.services.jwt.MyUserDetailsService;
import com.introtuce.introtuce.utils.JwtUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private StatusListRepository statusRepository;

    @Autowired
    private CommentsRepository commentsRepository;


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

    @GetMapping("fetch-user-id")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Bearer {{token}}", required = true, paramType = "header", example = "Bearer access_token")
    )
    public ResponseEntity<?> fetchUserId(){

        Map<String,Integer> data= new HashMap(){{
            put("id",1);
        }};
        return ResponseEntity.ok(data);
    }

    @GetMapping("status-list")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Bearer {{token}}", required = true, paramType = "header", example = "Bearer access_token")
    )
    public ResponseEntity<?> fetchStatusList(
            @RequestParam(name="user_id") Integer userId,
            @RequestParam(name="page_number",required = false) Integer pageNumber,
            @RequestParam(name = "page_size",required = false) Integer pageSize

    ){
         List statusList = statusRepository.findAll();
         return ResponseEntity.ok(statusList);
    }

    @PostMapping("status")
    public ResponseEntity<?> createStatus(
            @RequestBody StatusBody r
            ){

        List res = new ArrayList<>();
        return ResponseEntity.ok(res);
    }

    @PostMapping("comment")
    public ResponseEntity<?> createComment(
            @RequestBody StatusBody r
    ){
        List res= new ArrayList<>();
        return ResponseEntity.ok(res);
    }


//    a global @ExceptionHandler with the @ControllerAdvice annotation.
//    can be implemented



}
