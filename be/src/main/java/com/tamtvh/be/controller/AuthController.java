package com.tamtvh.be.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamtvh.be.dto.GetMe;
import com.tamtvh.be.message.request.LoginRequest;
import com.tamtvh.be.message.response.JwtResponse;
import com.tamtvh.be.security.jwt.JwtUtils;
import com.tamtvh.be.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUSERNAME(), loginRequest.getPASSWORD()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String role = roles.get(0);
        System.out.println(role);
        return ResponseEntity.ok().body(new JwtResponse(jwt, role));

//        if(role.equals("ROLE_ADMIN") || role.equals("ROLE_EMPLOYEE"))
//        {
//            Employee employee = employeeRepository.findByEmail(username).get();
//            JwtResponse jwtResponse = new JwtResponse();
////            boolean captchaVerified = captchaService.verify(loginRequest.getRecaptchaResponse());
//
//            jwtResponse.setToken(jwt);
//            jwtResponse.setId(employee.getId());
//            jwtResponse.setEmail(employee.getEmail());
//            jwtResponse.setPhone(employee.getPhone());
//            jwtResponse.setAddress(employee.getAddress());
//            jwtResponse.setFirstName(employee.getFirstname());
//            jwtResponse.setLastName(employee.getLastname());
//            jwtResponse.setGender(employee.getGender());
//            jwtResponse.setRole(role);
//
//            return ResponseEntity.ok().body(new CustomResponse(200, "Login successfully",
//                    jwtResponse));
//        } else {
//            User customer = userRepository.findByEmail(username).get();
//            JwtResponse jwtResponse = new JwtResponse();
////            boolean captchaVerified = captchaService.verify(loginRequest.getRecaptchaResponse());
//
//            jwtResponse.setToken(jwt);
//            jwtResponse.setId(customer.getId());
//            jwtResponse.setEmail(customer.getEmail());
//            jwtResponse.setPhone(customer.getPhone());
//            jwtResponse.setAddress(customer.getAddress());
//            jwtResponse.setFirstName(customer.getFirstname());
//            jwtResponse.setLastName(customer.getLastname());
//            jwtResponse.setGender(customer.getGender());
//            jwtResponse.setRole(role);
//
//            return ResponseEntity.ok().body(new CustomResponse(200, "Login successfully",
//                    jwtResponse));
//        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        System.out.println(token);
        token = token.substring(7);
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        GetMe getMe = new GetMe();
        try {
            Map<String, Object> map = new ObjectMapper().readValue(payload, Map.class);
            getMe.setUserId((String) map.get("userId"));
            getMe.setRole((String) map.get("role"));
            getMe.setUsername((String) map.get("sub"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(getMe);
    }
}
