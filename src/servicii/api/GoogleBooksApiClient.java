package servicii.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoogleBooksApiClient {
    private OkHttpClient httpClient;
    private Gson gson;

    public GoogleBooksApiClient() {
        httpClient = new OkHttpClient();
        gson = new GsonBuilder().create();
    }

    public List<Item> searchBooks(String title, String author, int maxResults) throws IOException {
        String query = buildQuery(title, author);

        if (query.isEmpty()) {
            throw new IllegalArgumentException("At least one of title or author must be provided.");
        }

        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + encodedQuery + "&maxResults=" + maxResults;

        Request request = new Request.Builder().url(url).build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                GoogleBooksResponse booksResponse = gson.fromJson(responseBody, GoogleBooksResponse.class);
                List<Item> items = new ArrayList<>();
                for(Item item: booksResponse.items) {
                    if (null!=item.volumeInfo.industryIdentifiers) {
                        items.add(item);
                    }
                }
                return items;
            } else {
                throw new IOException("Unsuccessful response: " + response.code());
            }
        }
    }

    private String buildQuery(String title, String author) {
        StringBuilder queryBuilder = new StringBuilder();
        if (title != null && !title.trim().isEmpty()) {
            queryBuilder.append("intitle:").append(title.trim());
        }
        if (author != null && !author.trim().isEmpty()) {
            if (queryBuilder.length() > 0) {
                queryBuilder.append("+");
            }
            queryBuilder.append("inauthor:").append(author.trim());
        }
        return queryBuilder.toString();
    }

}

