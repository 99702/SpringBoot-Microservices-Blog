package com.microservicesblog.databases.microservicesblogdb.repo;

import com.microservicesblog.databases.microservicesblogdb.entity.BlogUserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Repository
public interface RoleRepository extends JpaRepository<BlogUserRoles,Long> {
}
