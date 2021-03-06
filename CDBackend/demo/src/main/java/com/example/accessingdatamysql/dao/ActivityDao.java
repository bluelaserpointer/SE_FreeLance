package com.example.accessingdatamysql.dao;

import java.util.List;

import com.example.accessingdatamysql.Classes.PaginationDao;
import com.example.accessingdatamysql.entity.*;

public interface ActivityDao extends PaginationDao {

        // 获取一个活动信息
        Activity getOneActivity(Integer activityId);

        // 添加新活动
        Activity addNewActivity(Activity newActivity);

        // 更新活动信息
        Activity updateActivity(Activity updateActivity);

        // 获取所有活动的信息
        List<Activity> getAllActivities();

        // 删除活动
        String deleteActivities(List<Integer> ActivityIds);

        // 删除所有活动
        String deleteAll();

        // 删除活动
        List<Activity> deleteActivity(Integer activityId);
}
