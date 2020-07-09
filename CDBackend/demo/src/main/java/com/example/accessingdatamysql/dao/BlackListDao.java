package com.example.accessingdatamysql.dao;

// import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;

import com.example.accessingdatamysql.entity.*;

public interface BlackListDao {
        BlackList getOneBlackList(Integer BlackListId);

        String addNewBlackList(Integer userId);

        String updateBlackList(Integer BlackListId, List<Integer> blockIds);

        List<BlackList> getAllBlackLists();

        String deleteBlackLists(List<Integer> BlackListIds);

        String deleteAll();

}
