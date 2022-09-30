package com.admin.service.impl;

import com.admin.mapper.NewsMapper;
import com.admin.pojo.dto.news.*;
import com.admin.pojo.entity.NewsEntity;
import com.admin.pojo.entity.NewsTypeEntity;
import com.admin.pojo.vo.news.NewsInfoVo;
import com.admin.pojo.vo.news.NewsTypeVo;
import com.admin.pojo.vo.news.NewsVo;
import com.admin.service.NewsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;

    @Override
    public List<NewsTypeVo> getNewsTypeList(NewsTypeFilterDto newsTypeFilterDto) {
        PageHelper.startPage(newsTypeFilterDto.getPageNum(), newsTypeFilterDto.getPageSize());
        return newsMapper.getNewsTypeList(newsTypeFilterDto);
    }

    @Override
    public List<NewsTypeEntity> getNewsTypeByID(int newsTypeID) {
        return newsMapper.getNewsTypeByID(newsTypeID);
    }

    @Override
    public List<NewsVo> getNewsList(NewsFilterDto newsFilterDto) {
        PageHelper.startPage(newsFilterDto.getPageNum(), newsFilterDto.getPageSize());
        return newsMapper.getNewsList(newsFilterDto);
    }

    @Override
    public List<NewsEntity> getNewsByNewsTypeID(int newsTypeID) {
        return newsMapper.getNewsByNewsTypeID(newsTypeID);
    }

    @Override
    public List<NewsEntity> getNewsByID(int newsID) {
        return newsMapper.getNewsByID(newsID);
    }

    @Override
    public List<NewsInfoVo> getNewsInfo(NewsInfoDto newsInfoDto) {
        return newsMapper.getNewsInfo(newsInfoDto);
    }

    @Override
    public List<NewsTypeEntity> getNewsTypeByName(String name) {
        return newsMapper.getNewsTypeByName(name);
    }

    @Override
    public int getNewsTypeListTotalCount(NewsTypeFilterDto newsTypeFilterDto) {
        return newsMapper.getNewsTypeList(newsTypeFilterDto).size();
    }

    @Override
    public int getNewsListTotalCount(NewsFilterDto newsFilterDto) {
        return newsMapper.getNewsList(newsFilterDto).size();
    }

    @Override
    public boolean addNewsType(AddNewsTypeDto addNewsTypeDto) {
        return newsMapper.addNewsType(addNewsTypeDto);
    }

    @Override
    public boolean addNews(AddNewsDto addNewsDto) {
        return newsMapper.addNews(addNewsDto);
    }

    @Override
    public boolean deleteNewsType(DeleteNewsTypeDto deleteNewsTypeDto) {
        return newsMapper.deleteNewsType(deleteNewsTypeDto);
    }

    @Override
    public boolean deleteNews(DeleteNewsDto deleteNewsDto) {
        return newsMapper.deleteNews(deleteNewsDto);
    }

    @Override
    public boolean updateNewsType(UpdateNewsTypeDto updateNewsTypeDto) {
        return newsMapper.updateNewsType(updateNewsTypeDto);
    }

    @Override
    public void updateNewsReadCount(NewsInfoDto newsInfoDto) {
        newsMapper.updateNewsReadCount(newsInfoDto);
    }

    @Override
    public boolean updateNewsTypeStatus(UpdateNewsTypeStatusDto updateNewsTypeStatusDto) {
        return newsMapper.updateNewsTypeStatus(updateNewsTypeStatusDto);
    }

    @Override
    public boolean updateNewsStatus(UpdateNewsStatusDto updateNewsStatusDto) {
        return newsMapper.updateNewsStatus(updateNewsStatusDto);
    }

    @Override
    public boolean updateNews(UpdateNewsDto updateNewsDto) {
        return newsMapper.updateNews(updateNewsDto);
    }
}