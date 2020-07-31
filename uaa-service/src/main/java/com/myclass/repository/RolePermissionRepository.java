package com.myclass.repository;

import com.myclass.model.entity.Role;
import com.myclass.model.entity.RolePermission;
import com.myclass.model.response.PermissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    @Query("SELECT new com.myclass.model.response.PermissionResponse(p.id, p.name, p.description, CASE WHEN (r.id is not null) THEN true ELSE false END) " +
            "FROM Permission as p LEFT JOIN RolePermission as r " +
            "ON r.permission.id = p.id AND r.role = :role")
    List<PermissionResponse> findByRole(@Param("role") Role role);
}
