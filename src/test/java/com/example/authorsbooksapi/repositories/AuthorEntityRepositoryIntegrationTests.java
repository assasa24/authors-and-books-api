package com.example.authorsbooksapi.repositories;

import com.example.authorsbooksapi.TestDataUtil;
import com.example.authorsbooksapi.domain.entities.AuthorEntity;
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
public class AuthorEntityRepositoryIntegrationTests {
    private AuthorRepository underTest;

    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        Optional<AuthorEntity> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        AuthorEntity author1 = TestDataUtil.createTestAuthorA();
        AuthorEntity author2 = TestDataUtil.createTestAuthorB();
        AuthorEntity author3 = TestDataUtil.createTestAuthorC();
        underTest.save(author1);
        underTest.save(author2);
        underTest.save(author3);

        Iterable<AuthorEntity> results = underTest.findAll();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(author1, author2, author3);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorEntity author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        author.setName("bla");
        underTest.save(author);
        Optional<AuthorEntity> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorEntity author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        underTest.deleteById(author.getId());
        Optional<AuthorEntity> result = underTest.findById(author.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        AuthorEntity author1 = TestDataUtil.createTestAuthorA();
        AuthorEntity author2 = TestDataUtil.createTestAuthorB();
        AuthorEntity author3 = TestDataUtil.createTestAuthorC();

        underTest.save(author1);
        underTest.save(author2);
        underTest.save(author3);

        Iterable<AuthorEntity> results = underTest.ageLessThan(50);
        assertThat(results).hasSize(2);
        assertThat(results).containsExactly(author2, author3);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        AuthorEntity author1 = TestDataUtil.createTestAuthorA();
        AuthorEntity author2 = TestDataUtil.createTestAuthorB();
        AuthorEntity author3 = TestDataUtil.createTestAuthorC();

        underTest.save(author1);
        underTest.save(author2);
        underTest.save(author3);

        Iterable<AuthorEntity> results = underTest.ageGreaterThan(50);
        assertThat(results).hasSize(1);
        assertThat(results).containsExactly(author1);
    }


}

