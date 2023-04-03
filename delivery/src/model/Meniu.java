package model;

import java.util.List;

public class Meniu {
    private List<Produs> listaProduse;

    public Meniu(List<Produs> listaProduse) {
        this.listaProduse = listaProduse;
    }

    public List<Produs> getListaProduse() {
        return listaProduse;
    }

    public void setListaProduse(List<Produs> listaProduse) {
        this.listaProduse = listaProduse;
    }

    @Override
    public String toString() {
        return "Meniu{" +
                "listaProduse=" + listaProduse +
                '}';
    }
}


