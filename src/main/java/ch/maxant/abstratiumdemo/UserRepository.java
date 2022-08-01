package ch.maxant.abstratiumdemo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("from User u where u.name like :name")
    Iterable<User> findWithNameLike(@Param("name") String name);
}