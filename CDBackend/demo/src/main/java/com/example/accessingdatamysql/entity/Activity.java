package com.example.accessingdatamysql.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Activity", schema = "cardgame")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "fieldHandler" })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "activityId")
public class Activity {
    // 活动Id（PK）
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer activityId;
    // 活动名称
    private String activityName;
    // 活动类型（分为定期或限定）
    private String type;
    // 活动细节，包括描述与图片等
    @Transient
    private ActivityDetails activityDetails;

    public Activity() {
    }

    public Activity(String type, String activityName) {
        this.type = type;
        this.activityName = activityName;
    }

    public void setActivity(String type, String activityName) {
        this.type = type;
        this.activityName = activityName;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public ActivityDetails getActivityDetails() {
        return this.activityDetails;
    }

    public void setActivityDetails(ActivityDetails activityDetails) {
        this.activityDetails = activityDetails;
    }

}
