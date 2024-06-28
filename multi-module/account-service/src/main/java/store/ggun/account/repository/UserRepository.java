package store.ggun.account.repository;


import store.ggun.account.domain.model.UserModel;
import store.ggun.account.domain.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    Optional<UserModel> findByUsername(String username);

    List<UserDto> findByName(String name);

    List<UserDto> findUsersByJob(String job);

    @Modifying
    @Query("update users set token = :token where id = :id")
    void modifyTokenById(@Param("id") Long id ,@Param("token") String token);

    Boolean existsByUsername(String param);

}
