package com.stacksimplify.restservices.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CustomErrorDetails {
    private Date timestamp;
    private String message;
    @JsonProperty("error_details")
    private String errorDetails;
}
