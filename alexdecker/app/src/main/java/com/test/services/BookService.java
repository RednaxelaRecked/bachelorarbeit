package com.test.services;

import io.swagger.client.ApiException;
import io.swagger.client.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks() throws ApiException;

    String getDownloadUrlOfBook(Format format, String bookId) throws ApiException;
}
