package com.admin.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.admin.pojo.dto.common.SourceDto;
import com.alibaba.fastjson.JSONObject;
import com.admin.util.CommonUtils;
import com.admin.util.Result;
import com.admin.util.Config;
import java.util.Objects;
@RestController
@RequestMapping("/common")
public class CommonController {

    /**
     * @author hy 2022年7月16日下午12:38:29
     * 阿里云上传图片
     */
    @PostMapping("/uploadImg")
    public Result uploadImg(@Validated @RequestBody SourceDto sourceDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        try {
            JSONObject resInfo = new JSONObject();
            String[] splitstr;
            String fileB64, fileBstr, module, type;
            fileB64 = sourceDto.getImgB64();
            splitstr = fileB64.split(",");
            int a = splitstr[0].indexOf('/');
            int b = splitstr[0].indexOf(';');
            type = splitstr[0].substring(a + 1, b);
            if (!type.equals("jpg") && !type.equals("jpeg") && !type.equals("png")) {//jpg、jpeg、png
                return Result.showInfo("00000002", "图片格式有误", null);
            }
            fileBstr = splitstr[1];
            module = sourceDto.getModule();
            String basePath = CommonUtils.picModuleType(module);
            String url = CommonUtils.saveFileLocal(basePath, fileBstr, type);
            resInfo.put("url", Config.baseurl + url);
            return Result.showInfo("00000000", "Success", resInfo);
        } catch (Exception e) {
            return Result.showInfo("00000002", "上传失败", null);
        }
    }

    /**
     * @author hy 2022年7月16日下午12:38:29
     * 阿里云上传文件
     */
    @PostMapping("/uploadResource")
    public Result uploadResource(@Validated @RequestBody SourceDto sourceDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        try {
            JSONObject resInfo = new JSONObject();
            String fileB64, fileBstr, module, type;
            fileB64 = sourceDto.getImgB64();
            String[] splitstr = fileB64.split(",");
            int a = splitstr[0].indexOf('/');
            int b = splitstr[0].indexOf(';');
            type = splitstr[0].substring(a + 1, b);
            if (!type.equals("jpg") && !type.equals("jpeg") && !type.equals("png") && !type.equals("mp4") && !type.equals("pdf")
                    && !type.equals("vnd.openxmlformats-officedocument.wordprocessingml.document")&& !type.equals("msword")
                    && !type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet") && !type.equals("x-zip-compressed")) {
                //限定格式，jpg、jpeg、png，mp4、pdf、doc(msword)、docx(vnd.openxmlformats-officedocument.wordprocessingml.document)、excel
                return Result.showInfo("00000002", "资源格式有误", null);
            }
            if(type.equals("vnd.openxmlformats-officedocument.wordprocessingml.document")){
                type="docx";
            }
            if(type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
                type="xlsx";
            }
            fileBstr = splitstr[1];
            module = sourceDto.getModule();
            System.out.println("type6:"+type);
            if(module.equals("6") && !type.equals("x-zip-compressed")){
                return Result.showInfo("00000002", "资源格式有误", null);
            }
            String basePath = CommonUtils.sourceModuleType(module);
            String url = CommonUtils.saveFileLocal(basePath, fileBstr, type);
            resInfo.put("url", Config.baseurl + url);
            return Result.showInfo("00000000", "Success", resInfo);
        } catch (Exception e) {
            return Result.showInfo("00000002", "上传失败", null);
        }
    }
}