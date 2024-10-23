package modele;

public class Revista extends Articol{

    private final String nrEditie;
    private final String frecventa;
    private final String dataPublicare;
    private final String editor;

    public Revista(String titlu, String cod, String dataPublicare, String limba, int numarExemplareDisponibile, String descriere, String nrEditie, String frecventa, String editor){
        super(titlu, cod, dataPublicare, limba, descriere);
        this.nrEditie = nrEditie;
        this.frecventa = frecventa;
        this.dataPublicare = dataPublicare;
        this.editor = editor;
    }

    public String getNrEditie(){
        return nrEditie;
    }

    public String getFrecventa(){
        return frecventa;
    }

    public String getDataPublicare(){
        return dataPublicare;
    }

    public String getEditor(){
        return editor;
    }

    public String getIssn(){
        return this.cod;
    }

    @Override
    public String toString(){
        return "Titlu: " + getTitlu() + "\nEditor: " + getEditor();
    }

    @Override
    public void afisare(){
        System.out.println("Titlu: " + getTitlu() + "\nEditorul revistei: " + getEditor() + "\nEditia: " + getNrEditie() + "\nFrecventa publicarii: " + getFrecventa() + "\nissn: " + getCod() + "\nData publicarii: " + getDataPublicare() + "\nLimba: " + getLimba() + "\nExemplare disponibile: " + getNumarExemplareDisponibile() + "\nDescriere: " + getDescriere());
    }
}
