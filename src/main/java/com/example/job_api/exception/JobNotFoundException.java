package com.example.job_api.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(String message) {

        super(message);
    }
}
