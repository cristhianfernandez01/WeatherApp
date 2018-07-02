package com.testidea1.domain.repository;

import java.util.List;

public interface IDataRepository<TEntity> {
    List<TEntity> getAll() throws Exception;
    TEntity getById(int id) throws Exception;
    TEntity create(TEntity entity) throws Exception;
    TEntity delete(int id) throws Exception;
    TEntity update(int id, TEntity entity) throws Exception;
    TEntity getByUniqueField(String field, Object value) throws Exception;
    List<TEntity> getEntitiesByField(String field, Object value) throws Exception;
    List<TEntity> getEntitiesByClause(String clause, Object... args) throws Exception;
    TEntity getEntityByClause(String clause, Object... args) throws Exception;
}
