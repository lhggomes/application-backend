package com.api.applicationbackend.repositories.usermgt;

import com.api.applicationbackend.enums.RoleEnum;
import com.api.applicationbackend.model.usermgt.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);
}
