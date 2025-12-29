package com.example.final_project.Repository;



import com.example.final_project.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String roleUser);
}
