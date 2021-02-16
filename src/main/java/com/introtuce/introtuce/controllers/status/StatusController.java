package com.introtuce.introtuce.controllers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.introtuce.introtuce.dao.status.CommentBody;
import com.introtuce.introtuce.dao.status.StatusBody;
import com.introtuce.introtuce.entity.user.Comment;
import com.introtuce.introtuce.entity.user.Status;
import com.introtuce.introtuce.repository.user.status.CommentsRepository;
import com.introtuce.introtuce.repository.user.status.StatusListRepository;
import com.introtuce.introtuce.services.jwt.MyUserDetailsService;
import com.introtuce.introtuce.utils.JwtUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/")
public class StatusController {

    @Autowired
    MongoTemplate mongoTemplate;
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
        Map<String, String> token = new HashMap<>();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken("sai@gmail.com", "123456")
            );
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername("sai@gmail.com");
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            token.put("token", jwt);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        return ResponseEntity.ok(token);
    }

    @GetMapping("fetch-user-id")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Bearer {{token}}", required = true, paramType = "header", example = "Bearer access_token")
    )
    public ResponseEntity<?> fetchUserId() {

        Map<String, Integer> data = new HashMap() {{
            put("id", 1);
        }};
        return ResponseEntity.ok(data);
    }

    @GetMapping("status-list")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Bearer {{token}}", required = true, paramType = "header", example = "Bearer access_token")
    )
    public ResponseEntity<?> fetchStatusList(
            @RequestParam(name = "user_id") Integer userId,
            @RequestParam(name = "page_number", required = false) Integer pageNumber,
            @RequestParam(name = "page_size", required = false) Integer pageSize

    ) {
        Optional<Status> statusList = statusRepository.findByUserId(Long.valueOf(userId));
        return ResponseEntity.ok(statusList);
    }

    @PostMapping("status")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Bearer {{token}}", required = true, paramType = "header", example = "Bearer access_token")
    )
    public ResponseEntity<?> createStatus(
            @RequestBody StatusBody r
    ) {

        final Optional userDetails = userDetailsService.findById(Long.valueOf(r.getUserId()));

        Map<String, String> res = new HashMap() {{
//            put("userId",Long.valueOf(r.getUserId()));
//            put("msg","created");

        }};

        if (userDetails.isPresent()) {
            statusRepository.save(
                    new Status((int) statusRepository.count() + 1,
                            Long.valueOf(r.getUserId()), r.getDescription(),
                            r.getMediaUrl(),
                            Arrays.asList()
                    )
            );
            res.put("msg", "created");
        } else {
            res.put("msg", "user does not exist");
        }

        return ResponseEntity.ok(res);
    }

    @PostMapping("comment")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Bearer {{token}}", required = true, paramType = "header", example = "Bearer access_token")
    )
    public ResponseEntity<?> createComment(
            @RequestBody CommentBody r
    ) {
        Map<String, String> res = new HashMap() {{
        }};

        Optional status = statusRepository.findById(r.getStatusId());
        final Optional userDetails = userDetailsService.findById(Long.valueOf(r.getUserId()));

        if (status.isPresent() && userDetails.isPresent()) {
            commentsRepository.save(
                    new Comment(
                            (int) commentsRepository.count() + 1,
                            r.getUserId(),
                            r.getStatusId(),
                            r.getComment()
                    )
            );
            List comments = Collections.singletonList(status.get());
            ObjectMapper oMapper = new ObjectMapper();
            List commentIds = new ArrayList();
            Map requiredStatus = new HashMap();
            comments.forEach(x -> {
                Map y = oMapper.convertValue(x, Map.class);
                commentIds.add(y.get("id"));
                requiredStatus.putAll(y);
            });
            requiredStatus.put("comments", commentIds);
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(
                    requiredStatus.get("id")
            ));
            Update update = new Update();
            update.set("comments", commentIds);
            mongoTemplate.findAndModify(query, update, Status.class);
            res.put("msg", "created");

        } else {
            res.put("msg", "user or status does not exist");
        }

        return ResponseEntity.ok(res);
    }


//    a global @ExceptionHandler with the @ControllerAdvice annotation.
//    can be implemented


}
