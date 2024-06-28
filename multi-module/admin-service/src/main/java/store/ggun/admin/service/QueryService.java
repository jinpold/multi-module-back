package store.ggun.admin.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface QueryService<T> {

    List<T> findAll() throws SQLException;
    Optional<T> findById(Long id);
    Long count();
    boolean existsById(Long id);}
