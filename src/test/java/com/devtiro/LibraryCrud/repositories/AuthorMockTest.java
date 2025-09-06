package com.devtiro.LibraryCrud.repositories;


import com.devtiro.LibraryCrud.domain.AuthorEntity;
import com.devtiro.LibraryCrud.methodUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorMockTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Autowired
    public AuthorMockTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.mapper = new ObjectMapper();
    }

    @Test
    void createAuthorAndTestThat201IsCreated() throws Exception {
        AuthorEntity authorEntity = methodUtil.createAuthor1();
        authorEntity.setAuthorid(null);
        String s = mapper.writeValueAsString(authorEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/about")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s)).andExpect(MockMvcResultMatchers.status().isCreated());

    }


}
