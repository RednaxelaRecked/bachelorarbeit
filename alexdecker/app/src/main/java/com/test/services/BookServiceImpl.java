package com.test.services;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Book;
import io.swagger.client.model.Empty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class BookServiceImpl implements BookService {

    private static final String PROPERTY_FILENAME = "litello.properties";

    private static final String API_KEY = "APIKEY";

    private DefaultApi restAPI;

    public BookServiceImpl() throws IOException {
        ApiClient apiClient = new ApiClient();
        InputStream propertyFile = getProperty(PROPERTY_FILENAME);
        Properties properties = new Properties();
        properties.load(propertyFile);
        apiClient.setApiKey(properties.getProperty(API_KEY));
        this.restAPI = new DefaultApi(apiClient);
    }

    @Override
    public List<Book> getAllBooks() throws ApiException {
        List<Book> books = restAPI.booksSamplesGet();
        return books;
    }

    @Override
    public String getDownloadUrlOfBook(Format format, String bookId) throws ApiException {
        Empty downloadUrl = restAPI.booksSamplesBookidDownloadGet(format.name().toLowerCase(), bookId);
        return downloadUrl.getSignedUrl();
    }

    private InputStream getProperty(String propertyFilename) throws FileNotFoundException {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFilename);

        if (inputStream == null){
            throw new FileNotFoundException("property file '" + propertyFilename + "' not found in the classpath");
        }

        return inputStream;
    }


}
