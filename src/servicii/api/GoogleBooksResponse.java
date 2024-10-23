package servicii.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class GoogleBooksResponse {
    @SerializedName("items")
    public List<Item> items;
}
