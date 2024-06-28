package store.ggun.admin.service;
import store.ggun.admin.domain.model.AdminModel;
import store.ggun.admin.domain.model.Messenger;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService <T> {
    public abstract Messenger save (T t);

    public abstract List<T> findAll() throws SQLException;

    public abstract Optional<T> findById(long id);

    public abstract String count();

    public  abstract  Optional<T> getOne(String id);

    public  abstract  String delete(T t);

    public  abstract  String deleteAll();

    public  abstract  Boolean existsById(long id);


    public abstract Messenger insertMenuData(AdminModel adminModel) throws SQLException;
}