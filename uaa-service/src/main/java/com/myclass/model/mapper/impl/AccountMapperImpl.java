package com.myclass.model.mapper.impl;

import com.myclass.model.entity.Account;
import com.myclass.model.entity.AccountRole;
import com.myclass.model.entity.AccountRoleId;
import com.myclass.model.entity.Role;
import com.myclass.model.mapper.AccountMapper;
import com.myclass.model.request.AccountRequest;
import com.myclass.model.request.RoleRequest;
import com.myclass.model.response.AccountResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapperImpl implements AccountMapper {
    @Override
    public AccountResponse toAccountDTO(Account account) {
        if (account == null) {
            return null;
        }
        AccountResponse accountReponse = new AccountResponse();
        // Account
        accountReponse.setId(account.getId());
        accountReponse.setEmail(account.getEmail());
        accountReponse.setFullName(account.getFullName());
        accountReponse.setAvatar(account.getAvatar());
        accountReponse.setPhone(account.getPhone());
        accountReponse.setAccountNonExpired(account.isAccountNonExpired());
        accountReponse.setAccountNonLocked(account.isAccountNonLocked());
        accountReponse.setCredentialsNonExpired(account.isCredentialsNonExpired());
        accountReponse.setEnabled(account.isEnabled());
        // Role
        List<AccountRole> roles = account.getRoles();
        if (roles != null) {
            List<String> listRoleName = new ArrayList<>();
            long roleId = 0;
            for (AccountRole accountRole : roles) {
                roleId = accountRole.getRole().getId();
                listRoleName.add(accountRole.getRole().getName());
            }
            accountReponse.setRoleId(roleId);
            accountReponse.setRoles(listRoleName);
        }
        return accountReponse;
    }

    @Override
    public List<AccountResponse> toListAccountDTO(List<Account> accounts) {
        if (accounts == null) {
            return null;
        }
        List<AccountResponse> accountReponses = new ArrayList<>();
        for (Account account : accounts) {
            accountReponses.add(toAccountDTO(account));
        }
        return accountReponses;
    }

    @Override
    public Account toAccountEntity(Account account, AccountRequest accountRequest) {
        if (accountRequest.getId() > 0) {
            account.setId(accountRequest.getId());
        }
        account.setEmail(accountRequest.getEmail());
        account.setFullName(accountRequest.getFullName());
        account.setAvatar(accountRequest.getAvatar());
        account.setPhone(accountRequest.getPhone());
        account.setEnabled(accountRequest.isEnabled());
        return account;
    }

    @Override
    public List<AccountRole> toListAccountRoleEntity(Account account, List<RoleRequest> roles) {
        if (roles == null) {
            return null;
        }
        List<AccountRole> listItem = new ArrayList<>();
        for(RoleRequest rolePermission : roles){
            AccountRole accountRole = new AccountRole();
            AccountRoleId accountRoleId = new AccountRoleId();
            accountRoleId.setAccountId(account.getId());
            accountRoleId.setRoleId(rolePermission.getId());
            accountRole.setId(accountRoleId);
            // Account
            accountRole.setAccount(account);
            // Role
            Role role = new Role();
            role.setId(rolePermission.getId());
            accountRole.setRole(role);
            accountRole.setEnabled(rolePermission.isEnabled());
            listItem.add(accountRole);
        }
        return listItem;
    }
}
