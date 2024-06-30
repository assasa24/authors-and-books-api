package com.example.authorsbooksapi.repositories;

import com.example.authorsbooksapi.TestDataUtil;
import com.example.authorsbooksapi.domain.entities.AuthorEntity;
import com.example.authorsbooksapi.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityRepositoryIntegrationTests {
    private BookRepository underTest;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthorA();
        BookEntity book = TestDataUtil.createTestBookA(author);
        underTest.save(book);
        Optional<BookEntity> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        AuthorEntity author1 = TestDataUtil.createTestAuthorA();

        BookEntity book1 = TestDataUtil.createTestBookA(author1);
        underTest.save(book1);

        BookEntity book2 = TestDataUtil.createTestBookB(author1);
        underTest.save(book2);

        BookEntity book3 = TestDataUtil.createTestBookC(author1);
        underTest.save(book3);

        Iterable<BookEntity> results = underTest.findAll();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(book1, book2, book3);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorEntity author = TestDataUtil.createTestAuthorA();
        BookEntity book = TestDataUtil.createTestBookA(author);
        underTest.save(book);
        book.setTitle("bla");
        underTest.save(book);
        Optional<BookEntity> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity author = TestDataUtil.createTestAuthorA();

        BookEntity book = TestDataUtil.createTestBookA(author);
        underTest.save(book);

        underTest.deleteById(book.getIsbn());
        Optional<BookEntity> result = underTest.findById(book.getIsbn());
        assertThat(result).isEmpty();
    }
}
