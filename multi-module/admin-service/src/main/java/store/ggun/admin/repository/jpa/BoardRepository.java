package store.ggun.admin.repository.jpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import store.ggun.admin.domain.model.BoardModel;

import java.util.List;

@Repository
public interface BoardRepository  extends JpaRepository<BoardModel, Long>{

    List<BoardModel> getAllByOrderByContentDesc();
}