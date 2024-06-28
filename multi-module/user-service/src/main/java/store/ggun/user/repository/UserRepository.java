package store.ggun.user.repository;

import store.ggun.user.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>, UserDao {

    UserModel findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserModel findByEmail(String email);
}
