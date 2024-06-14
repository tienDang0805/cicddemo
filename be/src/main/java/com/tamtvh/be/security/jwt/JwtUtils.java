package com.tamtvh.be.security.jwt;

import com.tamtvh.be.model.Customer;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.security.services.UserDetailsImpl;
import com.tamtvh.be.service.CustomerService;
import com.tamtvh.be.service.StaffService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${tamtvh.app.jwtSecret}")
    private String jwtSecret;

    @Value("${tamtvh.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    CustomerService customerService;
    @Autowired
    StaffService staffService;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String role = roles.get(0);
        if (role.equals("staff") || role.equals("ADMIN") || role.equals("shipper")) {
            Staff staff = staffService.findByUsername(userPrincipal.getUsername());
            userPrincipal.setUserId(staff.getMANV());
        }
        if (role.equals("customer") || role.equals("KHACHHANG")) {
            Customer customer = customerService.findByUsername(userPrincipal.getUsername());
            userPrincipal.setUserId(customer.getMAKH());
        }
        return Jwts.builder()
                .claim("userId", userPrincipal.getUserId())
                .claim("role", role)
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((new Date(System.currentTimeMillis())).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
