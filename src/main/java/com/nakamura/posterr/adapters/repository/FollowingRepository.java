package com.nakamura.posterr.adapters.repository;


import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@UseClasspathSqlLocator
public interface FollowingRepository {

    @RegisterBeanMapper(FollowingEntity.class)
    @SqlQuery
    List<FollowingEntity> following(Long userId);

    @SqlUpdate
    void addFollowing(@BindBean FollowingEntity followingEntity);

}
