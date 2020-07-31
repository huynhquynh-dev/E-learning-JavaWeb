package com.myclass.service;

import com.myclass.model.entity.Account;
import com.myclass.model.request.AccountRequest;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(long accountId);

    Account saveOrUpdate(AccountRequest accountRequest);

    boolean delete(long accountId);
}
