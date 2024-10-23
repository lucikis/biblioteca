package modele;

import servicii.api.Item;

import java.util.List;
import java.util.Objects;

public class Carte extends Articol {

    private List<String> autori;
    private String editura;

    public Carte(String titlu, String cod, String dataPublicare, String limba, String descriere, List<String> autori, String editura){
        super(titlu, cod, dataPublicare, limba, descriere);
        this.autori = autori;
        this.editura = editura;
    }

    public List<String> getAutori(){
        return autori;
    }

    public String getEditura(){
        return editura;
    }

    public String getIsbn(){
        return this.cod;
    }

    @Override
    public void afisare() {
        System.out.println("Titlu: " + getTitlu() + "\nAutor(i) cartii: " + getAutori() + "\nisbn: " + getCod() + "\nData publicarii: " + getDataPublicare() + "\nLimba: " + getLimba() + "\nExemplare disponibile: " + getNumarExemplareDisponibile() + "\nDescriere: " + getDescriere());
    }

    public static Carte creeazaDinItem(Item item) {
        Carte carte = new Carte(item.volumeInfo.titlu, item.volumeInfo.getIsbn13(), item.volumeInfo.dataPublicarii, item.volumeInfo.limba, item.volumeInfo.descriere, item.volumeInfo.getNumeAutori(), item.volumeInfo.editura);
        return carte;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Carte)) return false;
        Carte other = (Carte) obj;
        return this.getCod().equals(other.getCod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCod());
    }

    @Override
    public String toString() {
        return this.getTitlu();
    }
}