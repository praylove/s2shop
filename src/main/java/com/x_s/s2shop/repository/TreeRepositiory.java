package com.x_s.s2shop.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

/**
 * 父子结构的 Repositiory
 */
@NoRepositoryBean
public interface TreeRepositiory<T, ID> extends BaseRepository<T, ID> {
    
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id in (:ids) or e.parentId in (:ids)")
    void deleteOrphanByIds(@Param("ids") ID ids);
    
}
