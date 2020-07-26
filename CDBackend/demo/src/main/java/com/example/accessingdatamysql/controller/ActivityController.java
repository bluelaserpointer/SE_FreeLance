package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

// import javax.validation.constraints.Null;

import com.example.accessingdatamysql.service.ActivityService;

@CrossOrigin(origins = "*")
@RestController // This means that this class is a Controller
@RequestMapping(path = "/activity") // This means URL's start with /demo (after Application path)
public class ActivityController {
  @Autowired // This means to get the bean called ActivityRepository
  // Which is auto-generated by Spring, we will use it to handle the data
  private ActivityService ActivityService;

  @RequestMapping(value = "/getActivity")
  public Activity findActivityByActivityId(@RequestParam("activityId") Integer activityId) {
    return ActivityService.getOneActivity(activityId);
  }

  @RequestMapping(value = "/addActivity")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public @ResponseBody Activity addNewActivity(@RequestBody Activity newAcitivity) {
    return ActivityService.addNewActivity(newAcitivity);

  }

  @RequestMapping(value = "/updateActivity")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public @ResponseBody Activity updateActivity(@RequestBody Activity updateActivity) {
    return ActivityService.updateActivity(updateActivity);
  }

  @RequestMapping(value = "/getAllActivities")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<Activity> getAllActivities() {
    return ActivityService.getAllActivities();
  }

  @RequestMapping(value = "/deleteActivities")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String deleteActivities(@RequestParam("activityIds") List<Integer> activityIds) {
    return ActivityService.deleteActivities(activityIds);
  }

  @RequestMapping(value = "/deleteAllActivities")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String deleteAll() {
    return ActivityService.deleteAll();
  }

  @RequestMapping(value = "/deleteActivity")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<Activity> deleteActivity(@RequestParam("activityId") Integer activityId) {
      return ActivityService.deleteActivity(activityId);
  }
}
 