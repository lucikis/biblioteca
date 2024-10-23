package modele;

public class Autor {
    private String nume;

    public Autor(String nume){
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    @Override
    public String toString(){
        return "Nume: " + nume;
    }
}
