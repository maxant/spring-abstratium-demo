package ch.maxant.abstratiumdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

// @NoRepositoryBean -> use that if we need our own interface from which a number of repos inherit
public interface UserRepository extends //
        CrudRepository<User, Integer>, //
        JpaRepository<User, Integer> // flush, by example, pageable, sort
{

    @Query("from User u where u.name like :name")
    Iterable<User> findWithNameLike(@Param("name") String name);

    @Modifying()
    @Query("update User u set modified = :modified")
    int updateAll(@Param("modified") LocalDateTime modified);

    // example without Query annotation: https://www.baeldung.com/spring-data-derived-queries
    Iterable<User> findByNameAndModifiedNotNull(@Param("name") String name);
}