package com.constructivist.cems.cems.repository;
import com.constructivist.cems.cems.model.User;
import com.constructivist.cems.cems.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {
}

