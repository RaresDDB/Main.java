package main;

import model.*;
import serviciu.ServiciuComenzi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Crearea unor produse pentru meniu
        Produs produs1 = new Produs("Pizza Margherita", 30.0);
        Produs produs2 = new Produs("Pizza Quattro Formaggi", 35.0);
        Produs produs3 = new Produs("Spaghetti Carbonara", 25.0);

        // Crearea unui meniu
        Meniu meniu = new Meniu(Arrays.asList(produs1, produs2, produs3));

        // Crearea unui local
        Local local1 = new Local("Pizzeria Napoli", "Strada Exemplu 1", meniu);

        // Crearea unor carduri
        Card card1 = new Card("1234567812345678", "05/2023", "Ion Popescu", "123");
        Card card2 = new Card("2345678923456789", "06/2023", "Maria Ionescu", "234");

        // Crearea unor utilizatori cu carduri
        Utilizator utilizator1 = new Utilizator(1, "Ion Popescu", "Strada Exemplu 2", "0712345678", card1);
        Utilizator utilizator2 = new Utilizator(2, "Maria Ionescu", "Strada Exemplu 3", "0723456789", card2);

        // Crearea unor comenzi
        Comanda comanda1 = new Comanda(1, new Date(), utilizator1, local1, Arrays.asList(produs1, produs2), 65.0, true);
        Comanda comanda2 = new Comanda(2, new Date(), utilizator2, local1, List.of(produs3), 25.0, true);
        Comanda comanda3 = new Comanda(3, new Date(), utilizator1, local1, List.of(produs2), 55.0, true);
        Comanda comanda4 = new Comanda(4, new Date(), utilizator2, local1, List.of(produs1, produs3), 108.0, false);

        // Crearea unor soferi
        Sofer sofer1 = new Sofer(3, "Gheorghe Popescu", "Strada Exemplu 4", "0734567890", "AB-13-XYZ");
        Sofer sofer2 = new Sofer(5, "Dumitru Vasile", "Strada Exemplu 5", "0734457568", "SB-11-ABC");

        // Adăugarea comenzilor la șofer
        sofer1.adaugaComanda(comanda1);
        sofer1.adaugaComanda(comanda2);
        sofer2.adaugaComanda(comanda3);

        // Crearea unei instante a clasei ServiciuComenzi
        ServiciuComenzi serviciu = new ServiciuComenzi();

        // Adaugarea datelor in serviciu
        serviciu.adaugaLocal(local1);
        serviciu.adaugaUtilizator(utilizator1);
        serviciu.adaugaUtilizator(utilizator2);
        serviciu.adaugaComanda(comanda1);
        serviciu.adaugaComanda(comanda2);
        serviciu.adaugaComanda(comanda3);
        serviciu.adaugaComanda(comanda4);
        serviciu.adaugaSofer(sofer1);
        serviciu.adaugaSofer(sofer2);

        // Interogare 1: cautare comanda dupa id
        System.out.println("Interogare 1: " + serviciu.cautaComandaDupaId(1));

        // interogare 2: cautare comenzi dintr-o anumita perioada
        try {
            Date dataInceput = dateFormat.parse("01-04-2023");
            Date dataSfarsit = dateFormat.parse("30-04-2023");
            System.out.println("Interogare 2: " + serviciu.cautaComenziDintrOAnumitaPerioada(dataInceput, dataSfarsit));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // interogare 3: calculare valoare totala a comenzilor plasate de un utilizator
        System.out.println("Interogare 3: " + serviciu.calculeazaValoareTotalaComenziUtilizator(utilizator1));

        // interogare 4: cautare local dupa adresa
        System.out.println("Interogare 4: " + serviciu.cautaLocalDupaAdresa("Strada Exemplu 1"));

        // interogare 5: calculare valoare totala a comenzilor plasate la un anumit local
        System.out.println("Interogare 5: " + serviciu.calculeazaValoareTotalaComenziLocal(local1));

        // interogare 6: cautare produs dupa nume si pret maxim
        System.out.println("Interogare 6: " + serviciu.cautaProdusDupaNumeSiPretMaxim("Pizza Margherita", 40.0));

        // interogare 7: cautare comenzi neefectuate
        System.out.println("Interogare 7: " + serviciu.cautaComenziNeefectuate());

        try {
            // interogare 8: calculare valoare totala a platilor efectuate intr-o anumita zi
            Date data = dateFormat.parse("03-04-2023");
            System.out.println("Interogare 8: " + serviciu.calculeazaValoareTotalaPlatiZi(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // interogare 9: cautare produse comandate de un utilizator la un anumit local
        System.out.println("Interogare 9: " + serviciu.cautaProduseComandateDeUtilizatorLaLocal(utilizator1, local1));

        // interogare 10: cautare utilizatori care au plasat cel putin o comanda
        System.out.println("Interogare 10: " + serviciu.cautaUtilizatoriCuComenziPlasate());

        // interogare 11: gasirea soferului cu cele mai multe comenzi finalizate
        System.out.println("Interogare 11: " + serviciu.gasesteSoferCuCeleMaiMulteComenziFinalizate());

        // interogare 12: gasirea utilizatorilor cu carduri care expira intr-o anumita luna si an
        System.out.println("Interogare 12: " + serviciu.cautaUtilizatoriCuCardExpiratInLunaSiAn(5, 2023));
    }
}
