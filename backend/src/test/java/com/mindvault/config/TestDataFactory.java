package com.mindvault.config;

import com.mindvault.user.entity.User;

public final class TestDataFactory {

    private TestDataFactory() {
    }

    public static User createUser() {

        User user = new User();

        user.setEmail("user@email.com");
        user.setName("Test User");

        return user;

    }

}
