package com.junshou.service.file.controller;

import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import com.junshou.service.file.util.FastDFSClient;
import com.junshou.service.file.util.FastDFSFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Api(value = "文件管理接口", description = "文件管理接口，提供页面的增、删、改、查")
public class FileController {

    /***
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public Result uploadFile(MultipartFile file) {
        try {
            //判断文件是否存在
            if (file == null) {
                throw new RuntimeException("文件不存在");
            }
            //获取文件的完整名称
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isEmpty(originalFilename)) {
                throw new RuntimeException("文件不存在");
            }

            //获取文件的扩展名称  abc.jpg   jpg
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            //获取文件内容
            byte[] content = file.getBytes();

            //创建文件上传的封装实体类
            FastDFSFile fastDFSFile = new FastDFSFile(originalFilename, content, extName);

            //基于工具类进行文件上传,并接受返回参数  String[]
            String[] uploadResult = FastDFSClient.upload(fastDFSFile);

            //封装返回结果
            String url = FastDFSClient.getTrackerUrl() + uploadResult[0] + "/" + uploadResult[1];
            return new Result(true, StatusCode.OK, "文件上传成功", url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, StatusCode.ERROR, "文件上传失败");
    }
}
