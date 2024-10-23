package modele;

public class Bibliotecar {
    private String id;
    private String nume;
    private String email;
    private String telefon;

    public Bibliotecar(String id, String nume, String email, String telefon){
        this.id = id;
        this.nume = nume;
        this.email = email;
        this.telefon = telefon;
    }

    public String getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void inregistreazaImprumut(Articol articol, Abonat abonat){
        if(articol.esteDisponibil()){
            abonat.imprumutaArticol(articol);
            articol.imprumuta();
        }else{
            System.out.println("Articolul " + articol.getTitlu() + " nu este disponibil");
        }
    }

    public void inregistreazaReturnare(Articol articol, Abonat abonat){
        if(abonat.getArticoleImprumutate().contains(articol)){
            abonat.returnareArticol(articol);
            articol.returneaza();
        }else{
            System.out.println("Articolul " + articol.getTitlu() + " este deja returnat");
        }
    }

}
