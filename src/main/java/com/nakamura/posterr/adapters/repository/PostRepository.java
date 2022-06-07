package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.adapters.repository.entity.PostEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@UseClasspathSqlLocator
public interface PostRepository {

    @SqlUpdate
    @GetGeneratedKeys
    Long addPost(@BindBean PostEntity postEntity);

    @SqlQuery
    @RegisterBeanMapper(PostEntity.class)
    Optional<PostEntity> getLastPostToday(Long userId);

    @SqlQuery
    @RegisterBeanMapper(PostEntity.class)
    List<PostEntity> getAllPost(int offset, int chunk);

    @SqlQuery
    @RegisterBeanMapper(PostEntity.class)
    List<PostEntity> getAllPostFromUserFollowed(Long userId, int offset, int chunk);

}
