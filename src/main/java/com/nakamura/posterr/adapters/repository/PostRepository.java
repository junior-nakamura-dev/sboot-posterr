package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.adapters.repository.entity.PostEntity;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@UseClasspathSqlLocator
public interface PostRepository {

    @SqlUpdate
    @GetGeneratedKeys
    Long addPost(@BindBean PostEntity postEntity);

}
