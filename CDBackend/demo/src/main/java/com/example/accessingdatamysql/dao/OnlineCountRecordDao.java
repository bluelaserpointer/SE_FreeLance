package com.example.accessingdatamysql.dao;

import com.example.accessingdatamysql.Classes.JSONPagination;
import com.example.accessingdatamysql.entity.OnlineCountRecord;

import java.sql.Timestamp;
import java.util.List;

public interface OnlineCountRecordDao extends JSONPagination {
    void saveCount();

    OnlineCountRecord getOnlineCountRecord();

    List<OnlineCountRecord> getAllOnlineCountRecords();

    List<OnlineCountRecord> getOnlineCountRecordByRange(Timestamp start, Timestamp end);

    List<OnlineCountRecord> getOnlineCountRecordsWithinHalfYear();

    List<OnlineCountRecord> getOnlineCountRecordsWithinOneDay();

}
