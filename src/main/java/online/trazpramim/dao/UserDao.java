package online.trazpramim.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import online.trazpramim.domain.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

}
