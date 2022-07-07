package com.example.demo.security.oauth;

import com.example.demo.security.JWTService;
import com.example.demo.user.LoginResponse;
import com.example.demo.user.TodoUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.demo.user.TodoUserService;
import com.example.demo.security.oauth.GithubAccessTokenResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/oauth")
public class GitHubController {

    private final RestTemplate restTemplate;
    private final String clientId;
    private final String clientSecret;
    private final TodoUserService todoUserService;
    private final JWTService jwtService;

    public GitHubController(RestTemplate restTemplate,
                            TodoUserService todoUserService,
                            JWTService jwtService,
                            @Value("${app.oauth.clientId}") String clientId,
                            @Value("${app.oauth.clientSecret}") String clientSecret) {
        this.restTemplate = restTemplate;
        this.todoUserService = todoUserService;
        this.jwtService = jwtService;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @GetMapping
    public LoginResponse handleGitHubRedirect(@RequestParam String code) {
        String url = "https://github.com/login/oauth/access_token?"
                + "client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&code=" + code;

        GithubAccessTokenResponse accessTokenResponse = restTemplate.postForObject(url, null, GithubAccessTokenResponse.class);

        ResponseEntity<GitHubUser> gitHubUser = restTemplate.exchange(
                "https://api.github.com/user",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(accessTokenResponse.getAccessToken())),
                GitHubUser.class
        );

        TodoUser user = todoUserService.createOrGet(gitHubUser.getBody());

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRole());
        String token = jwtService.createToken(new HashMap<>(), user.getId());

        return new LoginResponse(token);
    }

    private HttpHeaders createHeaders(String token){
        return new HttpHeaders() {{
            String authHeader = "Bearer " + token;
            set( "Authorization", authHeader );
        }};
    }

}
