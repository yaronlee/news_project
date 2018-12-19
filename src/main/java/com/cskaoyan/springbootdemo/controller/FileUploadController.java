package com.cskaoyan.springbootdemo.controller;


import com.cskaoyan.springbootdemo.service.AliyunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class FileUploadController {

    @Value("${upload-dir}")
    String uploadDir;
    @Autowired
    AliyunService aliyunService;



    /**
     * 通过SpringMVC完成文件上传操作
     * @param file
     * @return
     */
    @RequestMapping("/uploadImageBySpringMVC")
    @ResponseBody
    public HashMap uploadImageBySpringMVC(MultipartFile file) {

        HashMap map = new HashMap();

        //得到上传文件名
        String filename = file.getOriginalFilename();

        //文件应该保存在哪里，即如何设置路径
        File fileToSave = new File(uploadDir + filename);
        System.out.println("fileToSave path = " + fileToSave.getAbsolutePath());

        try {
            file.transferTo(fileToSave);
            map.put("code",0);
            //注意要再去写image controller
            map.put("msg","http://localhost/image?name="+filename);
        } catch (IOException e) {
            e.printStackTrace();
            //文件上传失败
            map.put("code",1);
            map.put("msg","error");
        }
        return map;
    }

    @RequestMapping("/uploadImage")
    @ResponseBody
    public HashMap uploadImageByAliyun(MultipartFile file) {

        HashMap map = new HashMap();
        try {
            String url = aliyunService.saveFileToOss(file);
            map.put("code",0);
            map.put("msg",url);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","error");
        }
        return map;
    }




    @RequestMapping("/image")
    public void showImage(String name, HttpServletResponse response){

        File file = new File(uploadDir + name);

        //将文件输入到内存中
        try {
            FileInputStream inputStream =  new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len=inputStream.read(buffer)) != -1){
                //注意这里是len
                outputStream.write(buffer, 0, len);
            }

            //最后要刷新一下
            outputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将文件输出到页面上

    }



}
