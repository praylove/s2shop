package com.x_s.s2shop.repository;

import com.x_s.s2shop.domain.BusinessFile;
import org.springframework.stereotype.Repository;

@Repository("fileRepository")
public interface BusinessFileRepository extends BaseRepository<BusinessFile, String> {


}
