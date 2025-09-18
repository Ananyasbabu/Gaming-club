package com.gaming.gaming.Services;
import org.springframework.stereotype.Service;


import com.gaming.gaming.Model.User;
import com.gaming.gaming.Repository.UserRepository;
import com.gaming.gaming.Security.JwtUtil;
import com.gaming.gaming.membership.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("Invalid username or password");
        }

        return jwtUtil.generateToken(username);
    }

    // Optional: method to register users with encoded passwords
    public User register(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null) {
            throw new BusinessException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
