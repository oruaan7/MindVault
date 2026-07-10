package com.mindvault.auth.dto;

public record LoginResponse(

    boolean authenticated,

    String message

) {
}
