package com.example.demo.Service;

import com.example.demo.Utils.RetCode;
import com.example.demo.Utils.RetResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

/**
 * @Autoor:杨文彬
 * @Date:2018/12/26
 * @Description：
 */
@Service
public class UploadService {

    @Value("${upload.dir.base}")
    private  String urlPrefix;

    public RetResult uploadFile(Map<String, Object> map) throws FileNotFoundException {
        try {
            MultipartFile uploadFile = (MultipartFile) map.get("uploadFile");
            if (uploadFile.isEmpty()) {
                return new RetResult(RetCode.FAIL.getCode(), "文件不能为空");
            }

            String fileName = uploadFile.getOriginalFilename();
            String filePath = urlPrefix;
            String fileType = uploadFile.getContentType();
            System.out.println(fileType);
            File file = new File(filePath + fileName);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            out.write(uploadFile.getBytes());
            out.flush();
            out.close();
            return new RetResult(RetCode.SUCCESS.getCode(),"上传成功",urlPrefix+uploadFile.getOriginalFilename());
        }catch (IOException e){
            e.printStackTrace();
            return new RetResult(RetCode.FALSE.getCode(),"上传失败");
        }

    }
}
