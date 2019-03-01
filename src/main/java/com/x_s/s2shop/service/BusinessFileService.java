package com.x_s.s2shop.service;

import com.x_s.s2shop.domain.BusinessFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface BusinessFileService extends BaseService<BusinessFile>{
    
    BusinessFile findByPath(String path);
    
    BusinessFile save(MultipartFile file);

    BusinessFile save(String fileType, String suffix);

    void use(String fileId, String userId);
    
    void deleteByPath(String path);
    
}
