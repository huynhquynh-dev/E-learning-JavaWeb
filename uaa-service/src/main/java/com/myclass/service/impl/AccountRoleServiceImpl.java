package com.myclass.service.impl;

import com.myclass.model.entity.Account;
import com.myclass.model.entity.AccountRole;
import com.myclass.model.entity.AccountRoleId;
import com.myclass.model.entity.Role;
import com.myclass.model.response.RoleResponse;
import com.myclass.repository.AccountRoleRepository;
import com.myclass.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {

    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Override
    public List<RoleResponse> findByAccount(long accountId) {
        Account account = new Account();
        account.setId(accountId);
        return accountRoleRepository.findByAccount(account);
    }

    @Override
    public AccountRole save(Account account, long roleId) {
        Role role = new Role();
        role.setId(roleId);
        accountRoleRepository.deleteByAccount(account);
        AccountRoleId accountRoleId = new AccountRoleId();
        accountRoleId.setAccountId(account.getId());
        accountRoleId.setRoleId(roleId);
        AccountRole accountRole = new AccountRole();
        accountRole.setId(accountRoleId);
        accountRole.setAccount(account);
        accountRole.setRole(role);
        accountRole.setEnabled(true);
        return accountRoleRepository.save(accountRole);
    }
}
