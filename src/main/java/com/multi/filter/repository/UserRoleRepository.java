package com.multi.filter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.multi.filter.entity.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

}
