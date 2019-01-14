package com.lbw;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Author by lbw , Date on 2018/10/10.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

  //  伪造web环境 并不会启动tomcat提高测试速度
  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void whenQuerySuccess() throws Exception {
    String result = mockMvc.perform(get("/user")
//        .param("username","xdd")
//        .param("username", "xdd")
//        .param("age", "120")
        .param("size", "10")
        .param("page", "0")
        .param("sort", "age,desc")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(3)).andReturn().getResponse().getContentAsString();

    System.out.println(result);
  }

  @Test
  public void whenGenInfoSuccess() throws Exception {
    String result = mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
    ).andExpect(status().isOk()).andExpect(jsonPath("$.username").value("xdd1")).andReturn()
        .getResponse().getContentAsString();
    System.out.println(result);
  }

  @Test
  public void whenGenInfoFail() throws Exception {
    mockMvc.perform(get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8)
    ).andExpect(status().is4xxClientError());
  }

  @Test
  public void whenCreateSuccess() throws Exception {
    Date date = new Date();
    System.out.println(date.getTime());
    String content = "{\"username\":null,\"age\":null," + "\"birthday\":" + date.getTime() + "}";
    String result = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(content))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1)).andReturn().getResponse().getContentAsString();
    System.out.println(result);
  }

  @Test
  public void whenCreateUpdate() throws Exception {
    Date date = new Date();
    System.out.println(date.getTime());
    String content = "{\"username\":null,\"age\":null," + "\"birthday\":" + date.getTime() + "}";
    String result = mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(content))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1)).andReturn().getResponse().getContentAsString();
    System.out.println(result);
  }

  @Test
  public void whenCreateDelete() throws Exception {
    mockMvc.perform(delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
  }

  @Test
  public void whenUploadSuccess() throws Exception {
    String result = mockMvc.perform(multipart("/file").file(
        new MockMultipartFile("file", "test.txt", "multipart/form-data",
            "hello world".getBytes("utf-8"))))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    System.out.println(result);
  }
}
