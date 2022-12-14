package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import com.admin.pojo.dto.common.ProvinceFilterDto;
import org.springframework.web.bind.annotation.*;
import com.admin.pojo.entity.ProvinceEntity;
import com.admin.pojo.dto.common.SourceDto;
import com.admin.service.ConfigService;
import com.alibaba.fastjson.JSONObject;
import com.admin.util.CommonUtils;
import com.admin.util.Result;
import com.admin.util.Config;
import java.util.Objects;
import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ConfigService configService;

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
                    && !type.equals("vnd.openxmlformats-officedocument.wordprocessingml.document") && !type.equals("msword")
                    && !type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet") && !type.equals("x-zip-compressed")) {
                //限定格式，jpg、jpeg、png，mp4、pdf、doc(msword)、docx(vnd.openxmlformats-officedocument.wordprocessingml.document)、excel
                return Result.showInfo("00000002", "资源格式有误", null);
            }
            if (type.equals("vnd.openxmlformats-officedocument.wordprocessingml.document")) {
                type = "docx";
            }
            if (type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                type = "xlsx";
            }
            if (type.equals("x-zip-compressed")) {
                type = "zip";
            }
            fileBstr = splitstr[1];
            module = sourceDto.getModule();
            String basePath = CommonUtils.sourceModuleType(module);
            String url = CommonUtils.saveFileLocal(basePath, fileBstr, type);
            resInfo.put("url", Config.baseurl + url);
            return Result.showInfo("00000000", "Success", resInfo);
        } catch (Exception e) {
            return Result.showInfo("00000003", "上传失败", null);
        }
    }

    //查询省列表
    @PostMapping("/getProvinceList")
    public Result getProvinceList(@Validated @RequestBody ProvinceFilterDto provinceFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<ProvinceEntity> provinceList = configService.getProvinceList(provinceFilterDto);
        int totalCount = configService.getProvinceListTotalCount(provinceFilterDto);
        return Result.showList("00000000", "Success", provinceList, totalCount);
    }
}