package modele;

import java.util.List;

public class Abonat {
    private String numeAbonat;
    private String CNP;
    private List<Articol> articoleImprumutate;

    public Abonat(String numeAbonat, String CNP, List<Articol> articoleImprumutate){
        this.numeAbonat = numeAbonat;
        this.CNP = CNP;
        this.articoleImprumutate = articoleImprumutate;
    }

    public String getNumeAbonat(){
        return numeAbonat;
    }

    public String getCNP(){
        return CNP;
    }

    public List<Articol> getArticoleImprumutate(){
        return articoleImprumutate;
    }

    public void imprumutaArticol(Articol articolImprumutat){
        articoleImprumutate.add(articolImprumutat);
    }

    public void returnareArticol(Articol articolReturnat){
        articoleImprumutate.remove(articolReturnat);
    }


    @Override
    public String toString() {
        return this.numeAbonat;
    }
}
