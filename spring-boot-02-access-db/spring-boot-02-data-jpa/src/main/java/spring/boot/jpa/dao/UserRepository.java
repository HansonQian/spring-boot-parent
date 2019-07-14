package spring.boot.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.jpa.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
