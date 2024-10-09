package com.example.Banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.Banking.entity.*;
import com.example.Banking.service.AccountService;

@RestController
@RequestMapping("/api/bank")
public class AccountController {
  
  @Autowired
  private AccountService accountService;

  @GetMapping("/{id}")
  public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
    AccountDto accountDto = accountService.getAccountById(id);
    return ResponseEntity.ok(accountDto);
  }

  @PostMapping
  public ResponseEntity<AccountDto> saveNewAccount(@RequestBody AccountDto accountDto){
    AccountDto accountDto2 = accountService.addNewAccount(accountDto);
    return new ResponseEntity<>(accountDto2 , HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<AccountDto>> getAllAccounts(){
    List<AccountDto> accounts = accountService.getAllAccounts();
    return ResponseEntity.ok(accounts);
  }

  @PutMapping("/{id}/deposit")
  public ResponseEntity<AccountDto>  depositMoney(@PathVariable Long id  ,@RequestBody Map<String , Double> request){
    
    Double amount = request.get("amount");
    AccountDto accountDto  = accountService.deposit(id, amount);
    return ResponseEntity.ok(accountDto);

  }

  @PutMapping("/{id}/withdraw")
  public ResponseEntity<AccountDto> withdrawMoney(@PathVariable Long id , @RequestBody Map<String , Double> request) {
    Double amount = request.get("amount");
    AccountDto accountDto =  accountService.withdrawMoney(id, amount);
    return ResponseEntity.ok(accountDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteAccountById(@PathVariable Long id){
    accountService.deleteAccount(id);
    return ResponseEntity.ok("Account was deleted");
  }

}
