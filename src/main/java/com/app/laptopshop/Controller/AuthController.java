package com.app.laptopshop.Controller;

import com.app.laptopshop.dto.LoginRequest;
import com.app.laptopshop.dto.SignupRequest;
import com.app.laptopshop.dto.JwtResponse;
import com.app.laptopshop.Service.UserDetailsServiceImpl;
import com.app.laptopshop.Service.UserService;
import com.app.laptopshop.Config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest request) {
        userService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
