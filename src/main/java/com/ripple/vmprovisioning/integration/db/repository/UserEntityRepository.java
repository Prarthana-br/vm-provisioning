package com.ripple.vmprovisioning.integration.db.repository;

import com.ripple.vmprovisioning.integration.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByName(String name);
}
