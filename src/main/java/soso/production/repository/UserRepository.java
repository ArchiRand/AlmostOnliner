package soso.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import soso.production.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
    User getByEmail(String email);
}
