package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.adapters.repository.entity.UserEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@UseClasspathSqlLocator
public interface UserRepository {

    @RegisterBeanMapper(UserEntity.class)
    @SqlQuery
    UserEntity getUser(Long userId);

}
