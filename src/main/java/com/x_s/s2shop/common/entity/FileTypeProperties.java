package com.x_s.s2shop.common.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class FileTypeProperties {

    private List<String> image;

}
