package com.example.Banking.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
  private Long id;
  private String accountHolderName;
  private double balance;
}
