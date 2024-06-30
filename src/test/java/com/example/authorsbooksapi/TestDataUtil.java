package com.example.authorsbooksapi;

import com.example.authorsbooksapi.domain.dto.AuthorDto;
import com.example.authorsbooksapi.domain.dto.BookDto;
import com.example.authorsbooksapi.domain.entities.AuthorEntity;
import com.example.authorsbooksapi.domain.entities.BookEntity;

public final class TestDataUtil {
    private TestDataUtil(){

    }

    public static AuthorEntity createTestAuthorA() {
        AuthorEntity author = AuthorEntity.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
        return author;
    }
    public static AuthorEntity createTestAuthorB() {
        AuthorEntity author = AuthorEntity.builder()
                .id(2L)
                .name("Thomas Cronin")
                .age(44)
                .build();
        return author;
    }
    public static AuthorEntity createTestAuthorC() {
        AuthorEntity author = AuthorEntity.builder()
                .id(3L)
                .name("Jesse A Cassey")
                .age(24)
                .build();
        return author;
    }

    public static BookEntity createTestBookA(AuthorEntity author) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static BookEntity createTestBookB(AuthorEntity author) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-1")
                .title("Beyond the Horizon")
                .author(author)
                .build();
    }

    public static BookEntity createTestBookC(AuthorEntity author) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Last Ember")
                .author(author)
                .build();
    }

    public static BookDto createTestBookDtoA(AuthorDto author) {
        return BookDto.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Last Ember")
                .author(author)
                .build();
    }

    public static BookEntity createTestBookEntityA(AuthorEntity author) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }
}
