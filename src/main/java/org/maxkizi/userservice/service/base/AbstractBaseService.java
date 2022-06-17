package org.maxkizi.userservice.service.base;

import org.maxkizi.userservice.model.BaseEntity;
import org.maxkizi.userservice.repository.BaseRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractBaseService<T extends BaseEntity, I extends Serializable, R extends BaseRepository<T, I>> implements BaseService<T, I> {
    public abstract R getRepository();

    @Override
    public Optional<T> get(I id) {
        return getRepository().findById(id);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    @Override
    public void delete(I id) {
        getRepository().deleteById(id);
    }

    @Override
    public boolean isExists(I id) {
        return getRepository().existsById(id);
    }
}
