package org.demo.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.demo.controllers.request.UserRequest;
import org.demo.controllers.response.UserResponse;
import org.demo.entity.User;
import org.demo.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static java.util.stream.Collectors.joining;
import static java.lang.String.format;

@RestController
@RequestMapping(path = "/")
@AllArgsConstructor
public class AuthController {
    public final AuthenticationManager authenticationManager;
    public final JwtEncoder jwtEncoder;
    public final UserService userService;
    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserRequest request){
        try{
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );
            var user = (User) authentication.getPrincipal();
            var now = Instant.now();
            var expiry = 36000L;
            var scope =
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(joining(" "));
            var claims =
                    JwtClaimsSet.builder()
                            .issuer("example.io")
                            .issuedAt(now)
                            .expiresAt(now.plusSeconds(expiry))
                            .subject(format("%s,%s", user.getId(), user.getUsername()))
                            .claim("roles", scope)
                            .build();

            var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
            var responseBody = new UserResponse();
            responseBody.setId(user.getId());
            responseBody.setUsername(user.getUsername());
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,token).body(responseBody);
        } catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
