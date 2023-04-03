package model;

public class Card {
    private String numar;
    private String dataExpirare;
    private String numeDetinator;
    private String cvv;

    public Card(String numar, String dataExpirare, String numeDetinator, String cvv) {
        this.numar = numar;
        this.dataExpirare = dataExpirare;
        this.numeDetinator = numeDetinator;
        this.cvv = cvv;
    }

    public String getNumar() {
        return numar;
    }

    public String getDataExpirare() {
        return dataExpirare;
    }

    public String getNumeDetinator() {
        return numeDetinator;
    }

    public String getCvv() {
        return cvv;
    }

    public void setNumar(String numar) {
        this.numar = numar;
    }

    public void setDataExpirare(String dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public void setNumeDetinator(String numeDetinator) {
        this.numeDetinator = numeDetinator;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Card{" +
                "numar='" + numar + '\'' +
                ", dataExpirare='" + dataExpirare + '\'' +
                ", numeDetinator='" + numeDetinator + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}

