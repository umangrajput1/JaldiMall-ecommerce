package com.jaldimall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDetails {

    private String accountNumber;

    private String accountHolderName;

    private String ifscCode;
}
