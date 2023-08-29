package com.microservicesblog.databases.microservicesblogdb.repo;

import com.microservicesblog.databases.microservicesblogdb.entity.BlogCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Repository
public interface BlogServiceRepo extends JpaRepository<BlogCollection,Long> {

    @Query(value = "SELECT b.* from blogs b where b.id = :id",nativeQuery = true)
    BlogCollection getBlogById(Long id);
}
