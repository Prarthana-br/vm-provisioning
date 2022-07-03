package com.ripple.vmprovisioning.integration.db.repository;

import com.ripple.vmprovisioning.integration.db.entity.VMEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface VMEntityRepository extends JpaRepository<VMEntity, Long> {

    Collection<VMEntity> findByUserId(String userId);
    Page<VMEntity> findAll(Pageable pageable);
}
