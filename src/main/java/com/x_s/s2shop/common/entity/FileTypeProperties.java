package com.x_s.s2shop.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FileTypeProperties {

    private List<String> image;
    
    public boolean isImage(String filename){
        String suffix = filename.substring(filename.lastIndexOf('.'));
        return image.contains(suffix);
    }

}
