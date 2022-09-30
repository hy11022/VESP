package com.admin.controller;

import com.admin.pojo.vo.news.NewsInfoVo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.admin.pojo.vo.news.NewsTypeVo;
import com.alibaba.fastjson.JSONObject;
import com.admin.pojo.vo.news.NewsVo;
import com.admin.service.NewsService;
import com.admin.util.CommonUtils;
import com.admin.pojo.dto.news.*;
import com.admin.pojo.entity.*;
import com.admin.util.JWTUtils;
import com.admin.util.Result;
import java.util.Objects;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    //获取新闻类别列表
    @PostMapping("/getNewsTypeList")
    public Result getNewsTypeList(@Validated @RequestBody NewsTypeFilterDto newsTypeFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<NewsTypeVo> newsTypeList = newsService.getNewsTypeList(newsTypeFilterDto);
        int totalCount = newsService.getNewsTypeListTotalCount(newsTypeFilterDto);
        return Result.showList("00000000", "Success", newsTypeList, totalCount);
    }

    //新增新闻类别
    @PostMapping("/addNewsType")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addNewsType(@Validated @RequestBody AddNewsTypeDto addNewsTypeDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        String name = addNewsTypeDto.getName();
        List<NewsTypeEntity> newTypeList = newsService.getNewsTypeByName(name);
        if (newTypeList.size() > 0) {
            return Result.showInfo("00000002", "该新闻类别名称已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addNewsTypeDto.setUpdateTime(now);
        boolean isSuccess = newsService.addNewsType(addNewsTypeDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除新闻类别
    @PostMapping("/deleteNewsType")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteNewsType(@Validated @RequestBody DeleteNewsTypeDto deleteNewsTypeDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int newsTypeID = deleteNewsTypeDto.getId();
        List<NewsTypeEntity> newsTypeList = newsService.getNewsTypeByID(newsTypeID);
        if (newsTypeList.size() < 1) {
            return Result.showInfo("00000002", "查无此新闻类别", null);
        }
        List<NewsEntity> newsList = newsService.getNewsByNewsTypeID(newsTypeID);
        if (newsList.size() > 0) {
            return Result.showInfo("00000003", "该新闻类别尚有新闻存在，不可删除", null);
        }
        boolean isSuccess = newsService.deleteNewsType(deleteNewsTypeDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "删除失败", null);
        }
    }

    //更新新闻类别
    @PostMapping("/updateNewsType")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateNewsType(@Validated @RequestBody UpdateNewsTypeDto updateNewsTypeDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int newsTypeID = updateNewsTypeDto.getId();
        List<NewsTypeEntity> newsTypeList = newsService.getNewsTypeByID(newsTypeID);
        if (newsTypeList.size() < 1) {
            return Result.showInfo("00000002", "查无此新闻类别", null);
        }
        String name = updateNewsTypeDto.getName();
        List<NewsTypeEntity> newTypeList = newsService.getNewsTypeByName(name);
        if (newTypeList.size() > 0) {
            return Result.showInfo("00000003", "该新闻类别名称已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateNewsTypeDto.setUpdateTime(now);
        boolean isSuccess = newsService.updateNewsType(updateNewsTypeDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "修改失败", null);
        }
    }

    //更新新闻类别状态
    @PostMapping("/updateNewsTypeStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateNewsTypeStatus(@Validated @RequestBody UpdateNewsTypeStatusDto updateNewsTypeStatusDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int newsTypeID = updateNewsTypeStatusDto.getId();
        List<NewsTypeEntity> newsTypeList = newsService.getNewsTypeByID(newsTypeID);
        if (newsTypeList.size() < 1) {
            return Result.showInfo("00000002", "查无此新闻类别", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateNewsTypeStatusDto.setUpdateTime(now);
        boolean isSuccess = newsService.updateNewsTypeStatus(updateNewsTypeStatusDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "修改失败", null);
        }
    }

    //获取新闻列表
    @PostMapping("/getNewsList")
    public Result getNewsList(@Validated @RequestBody NewsFilterDto newsFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<NewsVo> newsList = newsService.getNewsList(newsFilterDto);
        int totalCount = newsService.getNewsListTotalCount(newsFilterDto);
        return Result.showList("00000000", "Success", newsList, totalCount);
    }

    //新增新闻
    @PostMapping("/addNews")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addNews(@Validated @RequestBody AddNewsDto addNewsDto, @RequestHeader Map headers, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        JSONObject authorization;
        try {
            authorization = JSONObject.parseObject(headers.get("authorization").toString());
        } catch (Exception e) {
            return Result.showInfo("00000002", "入参有误", null);
        }
        String accessToken = authorization.getString("accessToken");
        String account = JWTUtils.getAccount(accessToken);
        String now = CommonUtils.getTime(0);    //获取当前时间
        addNewsDto.setUpdateTime(now);
        if(addNewsDto.getStatus().equals("1")){
            addNewsDto.setLastPublishtime(now);
            addNewsDto.setLastPublisherAccount(account);
        }
        boolean isSuccess = newsService.addNews(addNewsDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除新闻
    @PostMapping("/deleteNews")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteNews(@Validated @RequestBody DeleteNewsDto deleteNewsDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int newsID = deleteNewsDto.getId();
        NewsInfoDto newsInfoDto = new NewsInfoDto();
        newsInfoDto.setId(newsID);
        List<NewsInfoVo> newsInfo = newsService.getNewsInfo(newsInfoDto);
        if(newsInfo.get(0).getStatus().equals("1")){
            return Result.showInfo("00000010", "状态有误，请刷新", null);
        }
        List<NewsEntity> newsTypeList = newsService.getNewsByID(newsID);
        if (newsTypeList.size() < 1) {
            return Result.showInfo("00000002", "查无此新闻", null);
        }
        boolean isSuccess = newsService.deleteNews(deleteNewsDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "删除失败", null);
        }
    }

    //获取新闻信息
    @PostMapping("/getNewsInfo")
    public Result getNewsInfo(@Validated @RequestBody NewsInfoDto newsInfoDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<NewsInfoVo> newsInfo = newsService.getNewsInfo(newsInfoDto);
        if(newsInfoDto.getVisitSource()==2){
            if(newsInfo.get(0).getStatus().equals("2")){
                return Result.showInfo("00000002", "该新闻不存在",null);
            }else{
                newsService.updateNewsReadCount(newsInfoDto);
            }
        }
        JSONObject resInfo = (JSONObject)JSONObject.toJSON(newsInfo.get(0));//Entity转JSONObject
        return Result.showInfo("00000000", "Success", resInfo);
    }

    //更新新闻
    @PostMapping("/updateNews")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateNews(@Validated @RequestBody UpdateNewsDto updateNewsDto,@RequestHeader Map headers, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int newsID = updateNewsDto.getId();
        List<NewsEntity> newsTypeList = newsService.getNewsByID(newsID);
        if (newsTypeList.size() < 1) {
            return Result.showInfo("00000002", "查无此新闻", null);
        }
        JSONObject authorization;
        try {
            authorization = JSONObject.parseObject(headers.get("authorization").toString());
        } catch (Exception e) {
            return Result.showInfo("00000003", "入参有误", null);
        }
        String accessToken = authorization.getString("accessToken");
        String account = JWTUtils.getAccount(accessToken);
        String now = CommonUtils.getTime(0);    //获取当前时间
        if(updateNewsDto.getStatus().equals("1")){
            updateNewsDto.setLastPublishtime(now);
            updateNewsDto.setLastPublisherAccount(account);
        }
        updateNewsDto.setUpdateTime(now);
        boolean isSuccess = newsService.updateNews(updateNewsDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "修改失败", null);
        }
    }

    //更新新闻状态
    @PostMapping("/updateNewsStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateNewsStatus(@Validated @RequestBody UpdateNewsStatusDto updateNewsStatusDto,@RequestHeader Map headers, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int newsID = updateNewsStatusDto.getId();
        List<NewsEntity> newsList = newsService.getNewsByID(newsID);
        if (newsList.size() < 1) {
            return Result.showInfo("00000002", "查无此新闻", null);
        }
        JSONObject authorization;
        try {
            authorization = JSONObject.parseObject(headers.get("authorization").toString());
        } catch (Exception e) {
            return Result.showInfo("00000003", "入参有误", null);
        }
        String accessToken = authorization.getString("accessToken");
        String account = JWTUtils.getAccount(accessToken);
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateNewsStatusDto.setUpdateTime(now);
        if(updateNewsStatusDto.getStatus().equals("1")){
            updateNewsStatusDto.setLastPublishtime(now);
            updateNewsStatusDto.setLastPublisherAccount(account);
        }
        boolean isSuccess = newsService.updateNewsStatus(updateNewsStatusDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "修改失败", null);
        }
    }
}
