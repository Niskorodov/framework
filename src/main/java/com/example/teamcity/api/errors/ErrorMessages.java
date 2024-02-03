package com.example.teamcity.api.errors;

public class ErrorMessages {
    public static final String BUILD_CONFIG_ERROR_ID = "ID should start with a latin letter and contain only latin letters, digits and underscores (at most 225 characters).";
    public static final String BUILD_CONFIG_INTERNAL_SERVER_ERROR = "Error has occurred during request processing, status code: 500 (Internal Server Error).";
    public static final String PROJECT_CREATION_WITHOUT_URL = "URL must not be empty";
    public static final String PROJECT_CREATION_WITHOUT_NAME = "Project name must not be empty";

}
