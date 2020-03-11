package com.example.application.backend.config.security;


import com.example.application.backend.data.entity.User;

@FunctionalInterface
public interface CurrentUser {

	User getUser();
}
