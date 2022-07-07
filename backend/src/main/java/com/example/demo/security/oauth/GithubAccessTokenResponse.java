package com.example.demo.security.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubAccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

}