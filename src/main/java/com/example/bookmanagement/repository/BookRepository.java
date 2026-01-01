package com.example.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmanagement.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
