package model;

import java.util.Date;
import java.util.List;

public class Comanda {
    private int id;
    private Date data;
    private Utilizator utilizator;
    private Local local;
    private List<Produs> listaProduse;
    private double valoareTotala;
    private boolean efectuata;

    public Comanda(int id, Date data, Utilizator utilizator, Local local, List<Produs> listaProduse, double valoareTotala, boolean efectuata) {
        this.id = id;
        this.data = data;
        this.utilizator = utilizator;
        this.local = local;
        this.listaProduse = listaProduse;
        this.valoareTotala = valoareTotala;
        this.efectuata = efectuata;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public Local getLocal() {
        return local;
    }

    public List<Produs> getListaProduse() {
        return listaProduse;
    }

    public double getValoareTotala() {
        return valoareTotala;
    }

    public boolean isEfectuata() {
        return efectuata;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public void setListaProduse(List<Produs> listaProduse) {
        this.listaProduse = listaProduse;
    }

    public void setValoareTotala(double valoareTotala) {
        this.valoareTotala = valoareTotala;
    }

    public void setEfectuata(boolean efectuata) {
        this.efectuata = efectuata;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", data=" + data +
                ", utilizator=" + utilizator +
                ", local=" + local +
                ", listaProduse=" + listaProduse +
                ", valoareTotala=" + valoareTotala +
                ", efectuata=" + efectuata +
                '}';
    }
}
