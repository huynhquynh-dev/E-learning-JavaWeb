package com.myclass.repository;

import com.myclass.model.entity.Account;
import com.myclass.model.entity.AccountRole;
import com.myclass.model.entity.Role;
import com.myclass.model.response.RoleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {

    @Query("SELECT new com.myclass.model.response.RoleResponse(r.id, r.name, r.description, CASE WHEN (a.id is not null) THEN true ELSE false END) " +
            "FROM Role r LEFT JOIN AccountRole a " +
            "ON a.role.id = r.id AND a.account = :account")
    List<RoleResponse> findByAccount(@Param("account") Account account);

    void deleteByAccount(@Param("account") Account account);

    AccountRole findByAccountAndRole(@Param("account") Account account, @Param("role") Role role);
}
