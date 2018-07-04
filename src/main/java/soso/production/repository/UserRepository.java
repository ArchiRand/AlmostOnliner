package soso.production.repository;

import soso.production.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
    @Query("Select new soso.production.model.dto.UserDto(user.email, user.password) " +
            "from User user where user.email=:email and user.password=:password")
    User getByEmail(@Param("email") String email, @Param("password") String password);

    User getByEmail(String email);
}
