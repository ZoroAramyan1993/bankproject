package com.example.bank_aplication_demo.repository;

import com.example.bank_aplication_demo.role.RoleName;
import com.example.bank_aplication_demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
Optional<Role>findByRoleName(RoleName roleName);

}




