package com.edurbs.bill.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edurbs.bill.api.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
