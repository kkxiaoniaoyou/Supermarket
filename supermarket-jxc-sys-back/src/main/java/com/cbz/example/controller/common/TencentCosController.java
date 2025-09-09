package com.cbz.example.controller.common;

import com.cbz.example.common.Result;
import com.cbz.example.common.domain.UploadedFile;
import com.cbz.example.config.TencentCosConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/tencent/cos")
public class TencentCosController {

    @Autowired
    private COSClient cosClient;

    @Autowired
    private TencentCosConfig tencentCosConfig;

    /**
     * 单文件上传
     */
    @PostMapping("/upload")
    public Result<?> upload(@RequestParam("file") MultipartFile file, @RequestParam(value = "path", required = false) String path) throws IOException {

        if (file.isEmpty()) {
            return Result.fail(700100, "未选择文件");
        }

        // 文件名，带后缀
        String filename = file.getOriginalFilename();

        path = filePathCheck(path);

        String key = path + filename;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        String bucketName = tencentCosConfig.getBucketName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file.getInputStream(), metadata);
        // 上传
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        assert filename != null;
        String[] split = filename.split("\\.");
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < split.length - 1; i++) {
            name.append(split[i]);
        }

        String url = "https://" + tencentCosConfig.getUrl() + "/" + filename;

        UploadedFile uploadedFile = UploadedFile.builder()
                .fileName(filename)
                .name(name.toString())
                .suffix(split[split.length - 1])
                .url(url)
                .size(file.getSize())
                .contentType(file.getContentType())
                .path(path)
                .build();

        return Result.success(uploadedFile);
    }

    /**
     * 文件路径格式检查
     *
     * @param path 传入的路径
     * @return 开头不带/ 结尾要带/
     */
    private String filePathCheck(String path) {
        if (StringUtils.isEmpty(path)) {
            path = "";
        } else {
            if (path.startsWith("/")) {
                path = path.replaceFirst("/", "");
            }
            if (!path.endsWith("/")) {
                path += "/";
            }
        }
        return path;
    }
}
