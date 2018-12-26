package com.example.demo.Controller;


import com.example.demo.Service.UploadService;
import com.example.demo.Utils.RetCode;
import com.example.demo.Utils.RetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Autoor:杨文彬
 * @Date:2018/12/25
 * @Description：
 */
@RestController
public class UploadController {


    @Autowired
    private UploadService uploadService;
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping(value = "/upload")
    public RetResult upload (@RequestParam("uploadFile") MultipartFile uploadFile){

        try
        {
            logger.info("上传文件开始");
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uploadFile",uploadFile);
            return uploadService.uploadFile(map);
        }
        catch (Exception e){
            return new RetResult(RetCode.FALSE.getCode(),"上传失败");
        }


    }

}
