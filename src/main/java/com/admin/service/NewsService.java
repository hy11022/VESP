package com.admin.service;

import com.admin.pojo.dto.news.*;
import com.admin.pojo.entity.NewsEntity;
import com.admin.pojo.entity.NewsTypeEntity;
import com.admin.pojo.vo.news.NewsTypeVo;
import com.admin.pojo.vo.news.NewsVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {

    List<NewsTypeVo> getNewsTypeList(NewsTypeFilterDto newsTypeFilterDto);

    List<NewsTypeEntity> getNewsTypeByID(int newsTypeID);

    List<NewsVo> getNewsList(NewsFilterDto newsFilterDto);

    List<NewsEntity> getNewsByNewsTypeID(int newsTypeID);

    List<NewsEntity> getNewsByID(int newsID);

    List<NewsEntity> getNewsInfo(NewsInfoDto newsInfoDto);

    List<NewsTypeEntity> getNewsTypeByName(String name);

    int getNewsTypeListTotalCount(NewsTypeFilterDto newsTypeFilterDto);

    int getNewsListTotalCount(NewsFilterDto newsFilterDto);

    boolean addNewsType(AddNewsTypeDto addNewsTypeDto);

    boolean addNews(AddNewsDto addNewsDto);

    boolean deleteNewsType(DeleteNewsTypeDto deleteNewsTypeDto);

    boolean deleteNews(DeleteNewsDto deleteNewsDto);

    boolean updateNewsType(UpdateNewsTypeDto updateNewsTypeDto);

    void updateNewsReadCount(NewsInfoDto newsInfoDto);

    boolean updateNewsTypeStatus(UpdateNewsTypeStatusDto updateNewsTypeStatusDto);

    boolean updateNewsStatus(UpdateNewsStatusDto updateNewsStatusDto);

    boolean updateNews(UpdateNewsDto updateNewsDto);
}