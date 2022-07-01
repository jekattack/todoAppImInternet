package com.example.demo.user;


import com.example.demo.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TodoUserService todoUserService;
    private final JWTService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginData loginData){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword()));
            HashMap<String, Object> additionalTokenClaims = new HashMap<>();
            TodoUser user = todoUserService.findByName(loginData.getUsername()).orElseThrow();

            additionalTokenClaims.putIfAbsent("userid", user.getId());

            return ResponseEntity.ok(new LoginResponse(jwtService.createToken(additionalTokenClaims, loginData.getUsername())));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
