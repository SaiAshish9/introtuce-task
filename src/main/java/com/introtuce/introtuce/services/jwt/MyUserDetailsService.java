package com.introtuce.introtuce.services.jwt;
import com.introtuce.introtuce.utils.MyUserPrinciple;
import com.introtuce.introtuce.entity.user.User;
import com.introtuce.introtuce.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{

        User user = userRepository.findByEmail(s);
        System.out.println(user.getUsername() + "\n"+
                user.getEmail()+ "\n" +
                user.getPassword()+"\n");
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }

        return new MyUserPrinciple(user);

    }
}
