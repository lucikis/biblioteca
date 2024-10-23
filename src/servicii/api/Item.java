package servicii.api;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("volumeInfo")
    public VolumeInfo volumeInfo;

    @Override
    public String toString() {
        return volumeInfo != null ? volumeInfo.toString() : "Carte fără informații";
    }
}
