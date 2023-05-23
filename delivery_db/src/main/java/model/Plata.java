package model;

import java.util.Date;

public class Plata {
    private int id;
    private Date data;
    private double valoare;
    private Card card;

    private int cardId;

    public Plata(int id, Date data, double valoare, Card card) {
        this.id = id;
        this.data = data;
        this.valoare = valoare;
        this.card = card;
    }

    public Plata(Date data, double valoare, int cardId) {
        this.data = data;
        this.valoare = valoare;
        this.cardId = cardId;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public double getValoare() {
        return valoare;
    }

    public Card getCard() {
        return card;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardId(Card card) {
        if(card == null)
            return cardId;
        return card.getId();
    }

    @Override
    public String toString() {
        return "Plata{" +
                "id=" + id +
                ", data=" + data +
                ", valoare=" + valoare +
                ", card=" + (card == null ? "null" : card.toString()) +
                '}';
    }
}

