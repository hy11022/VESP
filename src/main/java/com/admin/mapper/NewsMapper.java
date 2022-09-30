package com.admin.mapper;

import com.admin.pojo.dto.news.*;
import com.admin.pojo.entity.NewsEntity;
import com.admin.pojo.entity.NewsTypeEntity;
import com.admin.pojo.vo.news.NewsInfoVo;
import com.admin.pojo.vo.news.NewsTypeVo;
import com.admin.pojo.vo.news.NewsVo;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface NewsMapper {

    List<NewsTypeVo> getNewsTypeList(NewsTypeFilterDto newsTypeFilterDto);

    @Select("SELECT * FROM news_types WHERE name = #{name}")
    List<NewsTypeEntity> getNewsTypeByName(String name);

    @Insert("INSERT INTO news_types SET name = #{name},update_time = #{updateTime}")
    boolean addNewsType(AddNewsTypeDto addNewsTypeDto);

    @Select("SELECT * FROM news_types WHERE id = #{newsTypeID}")
    List<NewsTypeEntity> getNewsTypeByID(int newsTypeID);

    @Select("SELECT * FROM news WHERE type_id = #{newsTypeID}")
    List<NewsEntity> getNewsByNewsTypeID(int newsTypeID);

    @Delete("DELETE FROM news_types WHERE id=#{id}")
    boolean deleteNewsType(DeleteNewsTypeDto deleteNewsTypeDto);

    @Update("UPDATE news_types SET name = #{name},update_time = #{updateTime} WHERE id=#{id}")
    boolean updateNewsType(UpdateNewsTypeDto updateNewsTypeDto);

    @Update("UPDATE news_types SET status = #{status},update_time = #{updateTime} WHERE id=#{id}")
    boolean updateNewsTypeStatus(UpdateNewsTypeStatusDto updateNewsTypeStatusDto);

    List<NewsVo> getNewsList(NewsFilterDto newsFilterDto);

    @Insert("INSERT INTO news SET name = #{name},type_id=#{typeID},introduction=#{introduction}," +
            "cover=#{cover},content=#{content},author=#{author},last_publisher_account = #{lastPublisherAccount}," +
            "last_publishtime=#{lastPublishtime},status = #{status},update_time = #{updateTime}")
    boolean addNews(AddNewsDto addNewsDto);

    @Select("SELECT * FROM news WHERE id = #{newsID}")
    List<NewsEntity> getNewsByID(int newsID);

    @Delete("DELETE FROM news WHERE id = #{id}")
    boolean deleteNews(DeleteNewsDto deleteNewsDto);

    @Select("SELECT a.*,b.name AS typeName FROM news a LEFT JOIN news_types b ON a.type_id = b.id WHERE a.id = #{id}")
    List<NewsInfoVo> getNewsInfo(NewsInfoDto newsInfoDto);

    boolean updateNews(UpdateNewsDto updateNewsDto);

    boolean updateNewsStatus(UpdateNewsStatusDto updateNewsStatusDto);

    @Update("UPDATE news SET read_count = read_count+1 WHERE id=#{id}")
    void updateNewsReadCount(NewsInfoDto newsInfoDto);
}