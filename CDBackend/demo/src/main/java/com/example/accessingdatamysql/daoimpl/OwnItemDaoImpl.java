package com.example.accessingdatamysql.daoimpl;

import com.example.accessingdatamysql.dao.OwnItemDao;
import com.example.accessingdatamysql.repository.*;
import com.example.accessingdatamysql.entity.*;

// import org.hibernate.validator.constraints.ISBN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
// import java.io.Console;
import java.util.*;

@Repository
public class OwnItemDaoImpl implements OwnItemDao {
    @Autowired
    private OwnItemRepository OwnItemRepository;

    @Override
    public OwnItem getOneOwnItem(Integer OwnItemId) {
        OwnItem OwnItem = OwnItemRepository.getOne(OwnItemId);
        return OwnItem;
    }

    public String addNewOwnItem(Integer userId, Integer itemId, Integer ItemCount) {
        Timestamp accquireDate = new Timestamp(System.currentTimeMillis());

        OwnItem ownItem = new OwnItem(userId, itemId, ItemCount, accquireDate);
        // System.out.println("new OwnItem has an Id of : " + n.getOwnItemId());
        OwnItemRepository.save(ownItem);
        return "Saved OwnItem";

    }

    public String updateOwnItem(Integer OwnItemId, Integer userId, Integer itemId, Integer ItemCount) {
        Timestamp accquireDate = new Timestamp(System.currentTimeMillis());
        OwnItem OwnItem = OwnItemRepository.getOne(OwnItemId);
        // System.out.println("old Card has an Id of : " + n.getCardId());
        OwnItem.setOwnItem(userId, itemId, ItemCount, accquireDate);

        OwnItemRepository.updateOwnItemStatus(OwnItem, OwnItemId);
        // return "Modified Card";
        // Image = Image.replace(' ', '+');
        return "modified OwnItem: " + OwnItem.getOwnItemId();

    }

    public List<OwnItem> getAllOwnItems() {
        List<OwnItem> OwnItems = OwnItemRepository.findAll();
        return OwnItems;
    }

    public List<OwnItem> getAllOwnItemsByUserId(Integer userId) {
        List<OwnItem> OwnItems = getAllOwnItems();
        List<OwnItem> UserOwnItems = new ArrayList<OwnItem>();
        for (int i = 0; i < OwnItems.size(); i++) {
            OwnItem OwnItem = OwnItems.get(i);
            if (OwnItem.getUserId().equals(userId)) {
                UserOwnItems.add(OwnItem);
            }
        }
        return UserOwnItems;
    }

    public String deleteOwnItems(List<Integer> OwnItemIds) {
        for (int i = 0; i < OwnItemIds.size(); i++) {
            OwnItemRepository.deleteById(OwnItemIds.get(i));
        }
        return "Deleted OwnItems by id";
    }

    public String deleteAll() {
        OwnItemRepository.deleteAll();
        return "Deleted All OwnItems";
    }

    public List<OwnItem> deleteOwnItem(Integer userId, Integer itemId)
    {
        OwnItemRepository.deleteOwnItemByUserIdEqualsAndItemIdEquals(userId, itemId);
        return getAllOwnItems();
    }
}