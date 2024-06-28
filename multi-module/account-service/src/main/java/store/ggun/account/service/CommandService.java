package store.ggun.account.service;

import store.ggun.account.domain.dto.Messenger;

import java.util.Optional;

public interface CommandService<T> {

    Messenger save (T t) ;
    Messenger deleteById (Long id);
    Optional<T> modify (T t);

}
