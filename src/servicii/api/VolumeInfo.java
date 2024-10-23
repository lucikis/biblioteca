package servicii.api;

import com.google.gson.annotations.SerializedName;
import modele.Autor;

import java.util.Date;
import java.util.List;

public class VolumeInfo {
    @SerializedName("title")
    public String titlu;

    @SerializedName("authors")
    public List<String> autori;

    @SerializedName("publisher")
    public String editura;

    @SerializedName("publishedDate")
    public String dataPublicarii;

    @SerializedName("description")
    public String descriere;

    @SerializedName("industryIdentifiers")
    public List<IndustryIdentifiers> industryIdentifiers;

    @SerializedName("language")
    public String limba;

    @Override
    public String toString() {
        return titlu + " - " + (autori != null ? String.join(", ", autori) : "Fără autori");
    }

    public String getIsbn13() {
        if (this.industryIdentifiers != null) {
            return this.industryIdentifiers.getFirst().identifier;
        }

        return null;
    }

    public List<String> getNumeAutori(){
        return autori;
    }
}
