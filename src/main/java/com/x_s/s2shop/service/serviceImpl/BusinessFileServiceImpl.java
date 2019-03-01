package com.x_s.s2shop.service.serviceImpl;

import com.x_s.s2shop.common.entity.FileTypeProperties;
import com.x_s.s2shop.common.exception.FileException;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.common.exception.ServiceException;
import com.x_s.s2shop.domain.BusinessFile;
import com.x_s.s2shop.repository.BusinessFileRepository;
import com.x_s.s2shop.service.BusinessFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service("fileService")
public class BusinessFileServiceImpl extends BaseServiceImpl<BusinessFile> implements BusinessFileService{

    @Autowired
    private BusinessFileRepository fileRepository;

    @Autowired
    private FileTypeProperties fileTypeProperties;

    @Value("${server-info.host}")
    private String host;
    
    @Value("${server-info.port}")
    private String port;
    
    @Override
    public BusinessFile findByPath(String path) {
        return fileRepository.findByPath(path);
    }
    
    @Override
    public BusinessFile save(MultipartFile file) {
        if (file.isEmpty()){
            throw new ParamException("上传文件不能为空！");
        }
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        if (!fileTypeProperties.isImage(originalFilename)){
            throw new ParamException("图片格式错误，请选择 " + fileTypeProperties.getImage() + " 的图片");
        }
        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        BusinessFile businessFile = this.save("图片", suffix);
        writeToDisk(file, businessFile.getFilename());
        return businessFile;
    }

    @Override
    @Transactional
    public BusinessFile save(String fileType, String suffix) {
        BusinessFile file = new BusinessFile();
        super.setCreateParam(file);
        file.setFilename(file.getId() + suffix);
        file.setFileType(fileType);
        file.setSuffix(suffix);
        file.setPath("http://" + host + ":" + port + "/file/" + file.getFilename());
        try {
            fileRepository.save(file);
        } catch (Exception e){
            throw new ServiceException(e);
        }
        return file;
    }

    @Override
    @Transactional
    public void use(String path, String bussinessId) {
        BusinessFile file = findByPath(path);
        if (file != null){
            file.setBussinessId(bussinessId);
            super.updateSelective(file);
        }
    }
    
    @Transactional
    public void deleteByPath(String path){
        try {
            fileRepository.deleteByPath(path);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private void writeToDisk(MultipartFile file, String filename){
        try {
            File dest = ResourceUtils.getFile("classpath:");
            Path path = Paths.get(dest.getAbsolutePath(), "files", filename);
            if (!Files.exists(path)){
                Files.createFile(path);
            }
            file.transferTo(path);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            throw new FileException("文件上传失败！");
        }
    }
}
