package com.myclass.model.mapper;

import com.myclass.model.entity.Account;
import com.myclass.model.entity.AccountRole;
import com.myclass.model.request.AccountRequest;
import com.myclass.model.request.RoleRequest;
import com.myclass.model.response.AccountResponse;

import java.util.List;

public interface AccountMapper {

    AccountResponse toAccountDTO(Account account);

    List<AccountResponse> toListAccountDTO(List<Account> accounts);

    Account toAccountEntity(Account account, AccountRequest accountRequest);

    List<AccountRole> toListAccountRoleEntity(Account account, List<RoleRequest> roles);
}
