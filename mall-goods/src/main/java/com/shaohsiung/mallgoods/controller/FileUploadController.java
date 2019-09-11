package com.shaohsiung.mallgoods.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.shaohsiung.mallgoods.constant.QiniuConstant;
import com.shaohsiung.mallgoods.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @Autowired
    private QiniuConstant qiniuConstant;

    @RequestMapping("/upload")
    @ResponseBody
    public Result uploadPicture(@RequestParam("file") MultipartFile multipartFile) {
        FileInputStream inputStream = null;
        String path = "";
        try {
            inputStream = (FileInputStream) multipartFile.getInputStream();
            path = uploadQiniuImg(inputStream, UUID.randomUUID().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.builder().status(200).message("图片上传成功").path(path).build();

        // 返回值
//        {
//            "path": "bigtable.s3-cn-south-1.qiniucs.com/dbc66ae1-aa14-4131-b087-a8e48d4d7b33",
//                "status": 200,
//                "message": "图片上传成功"
//        }
    }

    /**
     *  七牛云上传图片
     * @param inputStream 输入流
     * @param fileName 文件名称
     * @return 文件位置
     */
    private String uploadQiniuImg(FileInputStream inputStream, String fileName) throws QiniuException {
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(qiniuConstant.getAccessKey(), qiniuConstant.getSecretKey());
        String upToken = auth.uploadToken(qiniuConstant.getBucket());

        Response response = uploadManager.put(inputStream, fileName, upToken, null, null);
        // 解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        String result = qiniuConstant.getPath() + "/" + putRet.key;

        return result;
    }
}
