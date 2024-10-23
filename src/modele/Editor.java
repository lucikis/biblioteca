package modele;

import java.util.ArrayList;
import java.util.List;

public class Editor {
    private String nume;
    private String email;
    private String telefon;
    private List<Revista> revistePublicate;

    public Editor(String nume, String email, String telefon, List<Revista> revistePublicate){
        this.nume = nume;
        this.email = email;
        this.telefon = telefon;
        this.revistePublicate = revistePublicate;
    }

    public String getNume(){
        return nume;
    }

    public String getEmail(){
        return email;
    }

    public String getTelefon(){
        return telefon;
    }

    public List<Revista> getRevistePublicate(){
        return revistePublicate;
    }

    public void adaugaRevista(Revista revistaAdaugata){
        revistePublicate.add(revistaAdaugata);
    }

    public String toString(){
        List<String> titluReviste = new ArrayList<>();
        for(Revista reviste: getRevistePublicate()) {
            titluReviste.add(reviste.getTitlu());
        }
        return "Nume: " + nume + "\nEmail: " + email + "\nReviste publicate: " + String.join(", ", titluReviste);
    }
}
