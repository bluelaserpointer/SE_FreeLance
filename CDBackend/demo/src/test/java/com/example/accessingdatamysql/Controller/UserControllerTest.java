package com.example.accessingdatamysql.Controller;

import com.example.accessingdatamysql.AccessingDataMysqlApplicationTest;
import com.example.accessingdatamysql.controller.UserController;
import com.example.accessingdatamysql.service.UserService;
import com.example.accessingdatamysql.entity.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

// @WebMvcTest(UserController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTest extends AccessingDataMysqlApplicationTest {

        @Test
        public void contextLoads() {

        }

        // @Autowired
        private MockMvc mockMvc;

        @Autowired
        private UserController userController;

        @Autowired
        private WebApplicationContext context;

        @Test
        public void contexLoads() throws Exception {
                assertThat(userController).isNotNull();
        }

        @Autowired
        private UserService userService;

        private ObjectMapper om = new ObjectMapper();

        @Before
        public void setUp() {
                userService.addNewUser("test", "test", "test", "test");
                mockMvc = MockMvcBuilders.standaloneSetup(context).build();
        }

        @AfterEach
        void tearDown() {

        }

        @AfterAll
        static void tearDownAll() {
        }

        // 测试获取单个userId（需要在数据库中存在userId为1的用户）
        @Test
        @DisplayName("File: UserController Method: findUserByUserId")
        public void findUserByUserId() throws Exception {
                MvcResult result = mockMvc
                                .perform(get("/user/getUser?userId=1").contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                                .andReturn();
                System.out.println(result.getResponse().getContentAsString());
        }

        // 测试添加一个新用户
        @Test
        @DisplayName("File: UserController Method: addNewUser")
        public void addNewUser() throws Exception {
                // User testUser = new User("add2","add2","add2","add2");
                MvcResult result = mockMvc
                                .perform(get("/user/addUser?userName=add2&email=add2&password=add2&phoneNumber=add2")
                                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
                System.out.println(result.getResponse().getContentAsString());
        }

        // 测试更新一个用户
        @Test
        @DisplayName("File: UserController Method: updateUser")
        public void updateUser() throws Exception {
                MvcResult result = mockMvc.perform(get(
                                "/user/updateUser?userId=1&userName=add3&email=add3&password=add3&phoneNumber=add3&credits=1000&access=1&level=10&curExpPoint=10&stamina=5&money=99&grade=10&engKnowledge=5&mathKnowledge=100&chiKnowledge=2")
                                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk()).andExpect(jsonPath("$level").value("10"))
                                .andDo(MockMvcResultHandlers.print()).andReturn();
                System.out.println(result.getResponse().getContentAsString());
        }

        // 测试获取所有用户
        @Test
        @DisplayName("File: UserController Method: getAllUsers")
        public void getAllUsers() throws Exception {
                MvcResult result = mockMvc
                                .perform(get("/user/getAllUsers").contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
                System.out.println(result.getResponse().getContentAsString());
        }

        @Test
        @DisplayName("File: UserController Method: deleteUsers")
        public void deleteUsers() throws Exception {
                MvcResult result = mockMvc
                                .perform(get("/user/deleteUsers?userIds=4")
                                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
                System.out.println(result.getResponse().getContentAsString());
        }

        @Test
        @DisplayName("File: UserController Method: deleteAll")
        public void deleteAll() throws Exception {
                MvcResult result = mockMvc
                                .perform(get("/user/deleteAllUsers").contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
                System.out.println(result.getResponse().getContentAsString());
        }

        @Test
        @DisplayName("File: UserController Method: identifyUser")
        public void identifyUser() throws Exception {
                MvcResult result = mockMvc
                                .perform(get("/user/identifyUser?userName=naomi&password=4everppg")
                                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
                System.out.println(result.getResponse().getContentAsString());
        }

        @Test
        @DisplayName("File: UserController Method: deleteUser")
        public void deleteUser() throws Exception {
                MvcResult result = mockMvc
                                .perform(get("/user/deleteUser?userId=5").contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
                System.out.println(result.getResponse().getContentAsString());
        }

        @Test
        @DisplayName("File: UserController Method: addExp")
        public void addExp() throws Exception {
                MvcResult result = mockMvc
                                .perform(get("/user/addExp?userId=1&exp=100")
                                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
                System.out.println(result.getResponse().getContentAsString());
        }

}