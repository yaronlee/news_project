package com.cskaoyan.springbootdemo.service;

import com.aliyun.oss.OSSClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class AliyunService {

    //如何调用阿里云oss接口把文件给到阿里云
    public String saveFileToOss(MultipartFile file) throws IOException {

        // Endpoint以深圳为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAIh4X14ta9dws0";
        String accessKeySecret = "YaNc9oSKCgcvSyISGxbfZpfTzxZAd5";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);


        String bukectname="yaron";
        // 上传文件流。
        InputStream inputStream = file.getInputStream();
        //key是上传的该文件的唯一标识，通常需要加uuid


        String key= UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();
        ossClient.putObject(bukectname,key , inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        return "https://"+bukectname +".oss-cn-shenzhen.aliyuncs.com/" + key;

    }

}
