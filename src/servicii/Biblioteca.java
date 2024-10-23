package servicii;

import interfete.Imprumutabil;
import modele.Abonat;
import modele.Articol;
import modele.Carte;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Imprumutabil> listaArticole;
    private List<Abonat> listaAbonati;

    public Biblioteca() {
        listaAbonati = new ArrayList<>();
        listaArticole = new ArrayList<>();
    }

    public List<Imprumutabil> getListaArticole() {
        return listaArticole;
    }

    public List<Carte> getListaCartiDisponibile() {
        List<Carte> listaCartiDisponibile = new ArrayList<>();
        for (Imprumutabil imprumutabil: listaArticole) {
            if(imprumutabil instanceof Carte && imprumutabil.esteDisponibil()) {
                listaCartiDisponibile.add((Carte) imprumutabil);
            }
        }
        return listaCartiDisponibile;
    }

    public List<Abonat> getListaAbonati() {
        return listaAbonati;
    }

    public void adaugaArticol(Imprumutabil articolAdaugat){
        listaArticole.add(articolAdaugat);
    }

    public void stergeArticol(Imprumutabil articolSters){
        listaArticole.remove(articolSters);
    }

    public void imprumutareArticol(Articol articolImprumutat, Abonat abonat) {
        if (articolImprumutat.esteDisponibil()) {
            abonat.imprumutaArticol(articolImprumutat);
            articolImprumutat.imprumuta();
        } else {
            throw new IllegalStateException("Articolul nu este disponibil");
        }
    }

    public void returnareArticol(Articol articolReturnat, Abonat abonat){
        abonat.returnareArticol(articolReturnat);
        articolReturnat.returneaza();
    }

    public void inregistreazaAbonat(Abonat abonat){
        listaAbonati.add(abonat);
    }

    public void afisareArticoleDisponibile(){
        for(Imprumutabil articol: listaArticole) {
            if (articol.esteDisponibil()) {
                articol.afisare();
            }
        }
    }

    public void afisareArticoleImprumutate(Abonat abonat){
        if(abonat.getArticoleImprumutate().getFirst() == null){
            System.out.println("Abonatul nu are carti imprumutate");
        }
        for(Imprumutabil articol: abonat.getArticoleImprumutate()) {
            articol.afisare();
        }
    }
}
