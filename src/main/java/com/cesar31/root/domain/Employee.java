package com.cesar31.root.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Employee extends User {

    private BigDecimal salary;
    private UUID organization;
}
