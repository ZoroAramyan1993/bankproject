package com.example.bank_aplication_demo.payload;

public class JwtAuthenticationResponse {
    private String accessToken;
    String bearer = "Bearer";

    public JwtAuthenticationResponse(String accessToken){
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }
}
