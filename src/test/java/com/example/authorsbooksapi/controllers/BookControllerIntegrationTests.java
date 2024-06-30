package com.example.authorsbooksapi.controllers;

import com.example.authorsbooksapi.TestDataUtil;
import com.example.authorsbooksapi.domain.dto.BookDto;
import com.example.authorsbooksapi.domain.entities.BookEntity;
import com.example.authorsbooksapi.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(org.springframework.test.context.junit.jupiter.SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private BookService bookService;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    @Test
    public void testThatBookIsCreatedReturnsStatus201() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String createBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testThatCreateBookReturnsCreatedBook() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String createBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle()));
    }

    @Test
    public void testThatBookReturnsSavedBooksStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListBooksReturnsBook() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
        bookService.createOrUpdateBook(bookEntity.getIsbn(), bookEntity);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value(bookEntity.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(bookEntity.getTitle()));
    }

    @Test
    public void testThatGetBookReturnsStatus200() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
        bookService.createOrUpdateBook(bookEntity.getIsbn(), bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetBookReturnsStatus40() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatUpdateBookReturnsStatus200() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
        bookService.createOrUpdateBook(bookEntity.getIsbn(), bookEntity);

        bookEntity.setTitle("New Title");
        String bookJson = objectMapper.writeValueAsString(bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatUpdateBookReturnsUpdatedBook() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
        bookService.createOrUpdateBook(bookEntity.getIsbn(), bookEntity);

        bookEntity.setTitle("New Title");
        String bookJson = objectMapper.writeValueAsString(bookEntity);
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/books/" + bookEntity.getIsbn())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookJson)
                ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookEntity.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookEntity.getTitle()));
    }

    @Test
    public void testThatPartialUpdateBookReturnsStatus200() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
        bookService.createOrUpdateBook(bookEntity.getIsbn(), bookEntity);
        bookEntity.setTitle("New Title");
        String bookJson = objectMapper.writeValueAsString(bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/" + bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPartialUpdateBookReturnsUpdatedBook() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
        bookService.createOrUpdateBook(bookEntity.getIsbn(), bookEntity);
        bookEntity.setTitle("New Title");
        String bookJson = objectMapper.writeValueAsString(bookEntity);
        mockMvc.perform(
                        MockMvcRequestBuilders.patch("/books/" + bookEntity.getIsbn())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookJson)
                ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookEntity.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookEntity.getTitle()));
    }

    @Test
    public void testThatDeleteExistingBookReturnsStatus204() throws Exception{
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
        bookService.createOrUpdateBook(bookEntity.getIsbn(), bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/" + bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteNonExistingBookReturnsStatus404() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/99fsa556442")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
