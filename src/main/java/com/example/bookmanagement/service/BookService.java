package com.example.bookmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.entity.BookEntity;
import com.example.bookmanagement.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book create(Book book) {
        BookEntity entity = mapToEntity(book);
        return mapToDomain(repository.save(entity));
    }

    public List<Book> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    public Book findById(Long id) {
        BookEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Book not found with id " + id));

        return mapToDomain(entity);
    }

    public Book update(Long id, Book book) {
        BookEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Book not found with id " + id));

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setYear(book.getYear());

        return mapToDomain(repository.save(existing));
    }

    public void delete(Long id) {
        BookEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Book not found with id " + id));

        repository.delete(entity);
    }

    
    private Book mapToDomain(BookEntity entity) {
        return Book.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .year(entity.getYear())
                .build();
    }

    private BookEntity mapToEntity(Book book) {
        return BookEntity.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .year(book.getYear())
                .build();
    }
}
