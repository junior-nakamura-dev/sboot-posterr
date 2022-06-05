package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.adapters.repository.entity.FollowedEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@UseClasspathSqlLocator
public interface FollowedRepository {

    @RegisterBeanMapper(FollowedEntity.class)
    @SqlQuery
    List<FollowedEntity> followed(Long userId);

}
