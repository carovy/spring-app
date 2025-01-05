package integrador.app.services;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface BaseService<E> {

    public List<E> findAll() throws Exception;

    public List<E> findAll(Sort sort) throws Exception;

    public E findById(Long id) throws Exception;

    public E save(E entity) throws Exception;

    public boolean delete(Long id) throws Exception;
}