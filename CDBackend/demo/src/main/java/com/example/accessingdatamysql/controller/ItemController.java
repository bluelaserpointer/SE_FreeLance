package com.example.accessingdatamysql.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.accessingdatamysql.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.accessingdatamysql.GlobalConstants.general_page_size;
// import java.sql.Timestamp;
import java.util.*;

// import javax.validation.constraints.Null;

import com.example.accessingdatamysql.service.ItemService;

@CrossOrigin(origins = "*")
@RestController // This means that this class is a Controller
@RequestMapping(path = "/item") // This means URL's start with /demo (after Application path)
public class ItemController {
  @Autowired // This means to get the bean called ItemRepository
  // Which is auto-generated by Spring, we will use it to handle the data
  private ItemService ItemService;

  @RequestMapping(value = "/getItem")
  public Item findItemByItemId(@RequestParam("itemId") Integer itemId) {
    return ItemService.getOneItem(itemId);
  }

  @RequestMapping(value = "/addItem")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public @ResponseBody Item addNewItem(@RequestBody Item newItem) {
    return ItemService.addNewItem(newItem);
  }

  @RequestMapping(value = "/updateItem")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public @ResponseBody Item updateItem(@RequestBody Item updateItem) {
    System.out.println(updateItem);
    return ItemService.updateItem(updateItem);
  }

  // 获取指定页数的数据
  @RequestMapping(value = "/List")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public JSONObject ListPage(@RequestBody ListRequest ListRequest) {
    ListRequest.setPageSize(general_page_size);
    String request = JSON.toJSONString(ListRequest);
    System.out.print(request);
    JSONObject response = ItemService.ListPage(ListRequest);
    return response;
  }

  @RequestMapping(value = "/getAllItems")
  public List<Item> getAllItems() {
    return ItemService.getAllItems();
  }

  @RequestMapping(value = "/deleteItems")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String deleteItems(@RequestParam("itemIds") List<Integer> itemIds) {
    return ItemService.deleteItems(itemIds);
  }

  @RequestMapping(value = "/deleteAllItems")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String deleteAll() {
    return ItemService.deleteAll();
  }

  @RequestMapping(value = "/deleteItem")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<Item> deleteItem(@RequestParam("itemId") Integer itemId) {
    return ItemService.deleteItem(itemId);
  }

}
