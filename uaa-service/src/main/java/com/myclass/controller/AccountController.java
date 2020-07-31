package com.myclass.controller;

import com.myclass.model.mapper.AccountMapper;
import com.myclass.model.request.AccountRequest;
import com.myclass.model.response.AccountResponse;
import com.myclass.model.response.RestResult;
import com.myclass.model.response.RoleResponse;
import com.myclass.service.AccountRoleService;
import com.myclass.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRoleService accountRoleService;

    @Autowired
    AccountMapper accountMapper;

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('role_admin')")
    public Object findAll() {
        RestResult<List<AccountResponse>> result = new RestResult<>();
        result.ok(accountMapper.toListAccountDTO(accountService.findAll()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    public Object findById(@PathVariable(value = "id") long id) {
        RestResult<AccountResponse> result = new RestResult<>();
        result.ok(accountMapper.toAccountDTO(accountService.findById(id)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/user")
    public Object save(@RequestBody AccountRequest accountRequest) {
        RestResult<AccountResponse> result = new RestResult<>();
        result.ok(accountMapper.toAccountDTO(accountService.saveOrUpdate(accountRequest)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/user")
    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    public Object update(@RequestBody AccountRequest accountRequest) {
        RestResult<AccountResponse> result = new RestResult<>();
        result.ok(accountMapper.toAccountDTO(accountService.saveOrUpdate(accountRequest)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin')")
    public Object delete(@PathVariable(value = "id") long id) {
        RestResult<String> result = new RestResult<>();
        boolean temp = accountService.delete(id);
        if (temp) {
            result.ok("Xóa thành công!!");
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user/{id}/role")
    public Object findByRoles(@PathVariable(value = "id") long id) {
        RestResult<List<RoleResponse>> result = new RestResult<>();
        result.ok(accountRoleService.findByAccount(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
