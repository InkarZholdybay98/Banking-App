package com.example.Banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

import com.example.Banking.entity.*;
import com.example.Banking.repo.AccountRepository;
import com.example.Banking.service.mapper.AccountMapper;

@Service
public class AccountService {
  
  @Autowired
  private AccountRepository accountRepo;

  public AccountDto addNewAccount(AccountDto accountDto){
    Account account = AccountMapper.mapToAccount(accountDto);
    Account savedAccount = accountRepo.save(account);
    return AccountMapper.mapToAccountDto(savedAccount);
  }

  public List<AccountDto> getAllAccounts(){

    List<Account> accountsFromBase = accountRepo.findAll();
    System.out.println("Accounts from database: " + accountsFromBase.size());

    List<AccountDto> accounts = accountsFromBase.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    return accounts;

  }

  public void deleteAccount(Long id){

    Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
    accountRepo.deleteById(id);

  }

  public AccountDto getAccountById(Long id){
    Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Acount does not exist"));
    AccountDto accountDto = AccountMapper.mapToAccountDto(account);
    return accountDto;
  }

  public AccountDto deposit(Long id , double amount){

    Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));

    double total = account.getBalance() + amount;
    account.setBalance(total);
    Account savedAccount = accountRepo.save(account);

    return AccountMapper.mapToAccountDto(savedAccount);
  }

  public AccountDto withdrawMoney(Long id , double amount){

    Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));

    if (account.getBalance() < amount) {
      new RuntimeException("Unsufficinet amount");
    }

    double total = account.getBalance() - amount;
    account.setBalance(total);
    Account savedAccount = accountRepo.save(account);

    return AccountMapper.mapToAccountDto(savedAccount);
  }

}
