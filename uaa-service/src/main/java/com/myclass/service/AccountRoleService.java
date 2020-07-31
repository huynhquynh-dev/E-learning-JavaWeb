package com.myclass.service;

import com.myclass.model.entity.Account;
import com.myclass.model.entity.AccountRole;
import com.myclass.model.response.RoleResponse;

import java.util.List;

public interface AccountRoleService {

    List<RoleResponse> findByAccount(long accountId);

    AccountRole save(Account account, long roleId);
}
