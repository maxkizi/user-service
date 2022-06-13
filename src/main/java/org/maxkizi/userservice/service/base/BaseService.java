package org.maxkizi.userservice.service.base;

import org.maxkizi.userservice.model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity, I extends Serializable> {
    Optional<T> get(I id);

    List<T> findAll();

    T save(T t);

    void delete(I id);

    boolean isExists(I id);
}
