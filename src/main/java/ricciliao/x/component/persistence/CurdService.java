package ricciliao.x.component.persistence;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import ricciliao.x.component.exception.AbstractException;

import java.io.Serializable;

public interface CurdService<T extends Serializable> {

    Long insert(@Nonnull T t) throws AbstractException;

    Long update(@Nonnull T t) throws AbstractException;

    @Nullable
    T get(Long id) throws AbstractException;

    Long delete(@Nonnull T t) throws AbstractException;

}
