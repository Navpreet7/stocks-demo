package com.stocks.demo.utils;

import org.springframework.http.ResponseEntity;

import com.stocks.demo.model.ApiError;

public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(ApiError apiError) {
          return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}