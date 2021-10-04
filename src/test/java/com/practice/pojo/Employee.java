package com.practice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    @JsonProperty("")
    private String firstName;
    @JsonProperty("")
    private String lastName;
    @JsonProperty("")
    private String jobId;

    private int salary;


}
