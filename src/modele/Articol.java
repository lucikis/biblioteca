package modele;

import interfete.Imprumutabil;

public abstract class Articol implements Imprumutabil {
    private final String titlu;
    protected String cod;
    private final String dataPublicare;
    private final String limba;
    private int numarExemplareDisponibile = 1;
    private final String descriere;

    public Articol(String titlu, String cod, String dataPublicare, String limba, String descriere){
        this.titlu = titlu;
        this.cod = cod;
        this.dataPublicare = dataPublicare;
        this.limba = limba;
        this.descriere = descriere;
    }

    public String getTitlu(){
        return titlu;
    }

    public String getCod(){
        return cod;
    }

    public String getDataPublicare(){
        return dataPublicare;
    }

    public String getLimba(){
        return limba;
    }

    public int getNumarExemplareDisponibile(){
        return numarExemplareDisponibile;
    }

    public String getDescriere(){
        return descriere;
    }

    public void setNumarExemplareDisponibile(int numarExemplareDisponibile){
        this.numarExemplareDisponibile = numarExemplareDisponibile;
    }

    public boolean esteDisponibil(){
        return numarExemplareDisponibile > 0;
    }

    public void imprumuta(){
        numarExemplareDisponibile--;
    }

    public void returneaza(){
        numarExemplareDisponibile++;
    }

    @Override
    public String toString() {
        return this.getTitlu();
    }
}
