package main;

import model.*;
import serviciu.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
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

        System.out.println();
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Meniu Principal:");
            System.out.println("1. Manipulare Carduri");
            System.out.println("2. Manipulare Utilizatori");
            System.out.println("3. Manipulare Produse");
            System.out.println("4. Manipulare Plati");
            System.out.println("5. Iesire");

            int option = scanner.nextInt();

            switch (option) {
                case 1 ->
                    // Manipulare Carduri
                        handleCardOptions();
                case 2 ->
                    // Manipulare Utilizatori
                        handleUtilizatorOptions();
                case 3 ->
                    // Manipulare Produse
                        handleProdusOptions();
                case 4 ->
                    // Manipulare Plati
                        handlePlataOptions();
                case 5 -> continueLoop = false;
                default -> System.out.println("Optiune invalida. Va rugam sa alegeti din nou.");
            }
        }

        scanner.close();
    }

    private static void handleCardOptions() {
        Scanner scanner = new Scanner(System.in);
        CardService cardService = CardService.getSingleInstance();
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("Meniu Carduri:");
            System.out.println("1. Creare card");
            System.out.println("2. Citire card");
            System.out.println("3. Actualizare card");
            System.out.println("4. Stergere card");
            System.out.println("5. Revenire la meniul principal");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    // Creare card
                    System.out.println("Introduceti numarul cardului:");
                    String numar = scanner.nextLine();
                    System.out.println("Introduceti data de expirare (MM/YY):");
                    String dataExpirare = scanner.nextLine();
                    System.out.println("Introduceti numele detinatorului:");
                    String numeDetinator = scanner.nextLine();
                    System.out.println("Introduceti CVV:");
                    String cvv = scanner.nextLine();
                    Card card = new Card(numar, dataExpirare, numeDetinator, cvv);
                    cardService.createCard(card);
                    AuditService.getSingleInstance().writeAuditLog("creare_card");
                }
                case 2 -> {
                    // Citire card
                    System.out.println("Introduceti ID-ul cardului pe care doriti sa il cititi:");
                    int id = scanner.nextInt();
                    Card cardRead = cardService.readCard(id);
                    if (cardRead != null) {
                        System.out.println(cardRead);
                        AuditService.getSingleInstance().writeAuditLog("citire_card");
                    }
                }
                case 3 -> {
                    // Actualizare card
                    System.out.println("Introduceti ID-ul cardului pe care doriti sa il actualizati:");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Card cardToUpdate = cardService.readCard(idToUpdate);
                    if (cardToUpdate != null) {
                        System.out.println("Introduceti noul nume al detinatorului:");
                        String newNumeDetinator = scanner.nextLine();
                        cardToUpdate.setNumeDetinator(newNumeDetinator);
                        cardService.updateCard(cardToUpdate);
                        AuditService.getSingleInstance().writeAuditLog("actualizare_card");
                    }
                }
                case 4 -> {
                    // Stergere card
                    System.out.println("Introduceti ID-ul cardului pe care doriti sa il stergeti:");
                    int idToDelete = scanner.nextInt();
                    cardService.deleteCard(idToDelete);
                    AuditService.getSingleInstance().writeAuditLog("stergere_card");
                }
                case 5 -> continueLoop = false;
                default -> System.out.println("Optiune invalida. Va rugam sa alegeti din nou.");
            }
        }
    }

    private static void handleUtilizatorOptions() {
        Scanner scanner = new Scanner(System.in);
        UtilizatorService utilizatorService = UtilizatorService.getSingleInstance();
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("Meniu Utilizatori:");
            System.out.println("1. Creare utilizator");
            System.out.println("2. Citire utilizator");
            System.out.println("3. Actualizare utilizator");
            System.out.println("4. Stergere utilizator");
            System.out.println("5. Revenire la meniul principal");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    // Creare utilizator
                    System.out.println("Introduceti numele utilizatorului:");
                    String nume = scanner.nextLine();
                    System.out.println("Introduceti adresa utilizatorului:");
                    String adresa = scanner.nextLine();
                    System.out.println("Introduceti numarul de telefon al utilizatorului:");
                    String numarTelefon = scanner.nextLine();
                    System.out.println("Introduceti ID-ul cardului utilizatorului:");
                    int cardId = scanner.nextInt();
                    Utilizator utilizator = new Utilizator(nume, adresa, numarTelefon, cardId);
                    utilizatorService.createUtilizator(utilizator);
                    AuditService.getSingleInstance().writeAuditLog("creare_utilizator");
                }
                case 2 -> {
                    // Citire utilizator
                    System.out.println("Introduceti ID-ul utilizatorului pe care doriti sa il cititi:");
                    int id = scanner.nextInt();
                    Utilizator utilizatorRead = utilizatorService.readUtilizator(id);
                    if (utilizatorRead != null) {
                        System.out.println(utilizatorRead);
                        AuditService.getSingleInstance().writeAuditLog("citire_utilizator");
                    }
                }
                case 3 -> {
                    // Actualizare utilizator
                    System.out.println("Introduceti ID-ul utilizatorului pe care doriti sa il actualizati:");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Utilizator utilizatorToUpdate = utilizatorService.readUtilizator(idToUpdate);
                    if (utilizatorToUpdate != null) {
                        System.out.println("Introduceti noul nume al utilizatorului:");
                        String newNume = scanner.nextLine();
                        utilizatorToUpdate.setNume(newNume);
                        utilizatorService.updateUtilizator(utilizatorToUpdate);
                        AuditService.getSingleInstance().writeAuditLog("actualizare_utilizator");
                    }
                }
                case 4 -> {
                    // Stergere utilizator
                    System.out.println("Introduceti ID-ul utilizatorului pe care doriti sa il stergeti:");
                    int idToDelete = scanner.nextInt();
                    utilizatorService.deleteUtilizator(idToDelete);
                    AuditService.getSingleInstance().writeAuditLog("stergere_utilizator");
                }
                case 5 -> continueLoop = false;
                default -> System.out.println("Optiune invalida. Va rugam sa alegeti din nou.");
            }
        }
    }

    private static void handlePlataOptions() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        PlataService plataService = PlataService.getSingleInstance();
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("Meniu Plati:");
            System.out.println("1. Creare plata");
            System.out.println("2. Citire plata");
            System.out.println("3. Actualizare plata");
            System.out.println("4. Stergere plata");
            System.out.println("5. Revenire la meniul principal");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    // Creare plata
                    System.out.println("Introduceti suma platii:");
                    double suma = scanner.nextDouble();
                    System.out.println("Introduceti ID-ul cardului pentru plata:");
                    int cardId = scanner.nextInt();
                    Plata plata = new Plata(new Date(), suma, cardId);
                    plataService.createPlata(plata);
                    AuditService.getSingleInstance().writeAuditLog("creare_plata");
                }
                case 2 -> {
                    // Citire plata
                    System.out.println("Introduceti ID-ul platii pe care doriti sa o cititi:");
                    int id = scanner.nextInt();
                    Plata plataRead = plataService.readPlata(id);
                    if (plataRead != null) {
                        System.out.println(plataRead);
                        AuditService.getSingleInstance().writeAuditLog("citire_plata");
                    }
                }
                case 3 -> {
                    // Actualizare plata
                    System.out.println("Introduceti ID-ul platii pe care doriti sa o actualizati:");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Plata plataToUpdate = plataService.readPlata(idToUpdate);
                    if (plataToUpdate != null) {
                        System.out.println("Introduceti noua suma a platii:");
                        double newSuma = scanner.nextDouble();
                        plataToUpdate.setValoare(newSuma);
                        plataService.updatePlata(plataToUpdate);
                        AuditService.getSingleInstance().writeAuditLog("actualizare_plata");
                    }
                }
                case 4 -> {
                    // Stergere plata
                    System.out.println("Introduceti ID-ul platii pe care doriti sa o stergeti:");
                    int idToDelete = scanner.nextInt();
                    plataService.deletePlata(idToDelete);
                    AuditService.getSingleInstance().writeAuditLog("stergere_plata");
                }
                case 5 -> continueLoop = false;
                default -> System.out.println("Optiune invalida. Va rugam sa alegeti din nou.");
            }
        }
    }

    private static void handleProdusOptions() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ProdusService produsService = ProdusService.getSingleInstance();
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("Meniu Produse:");
            System.out.println("1. Creare produs");
            System.out.println("2. Citire produs");
            System.out.println("3. Actualizare produs");
            System.out.println("4. Stergere produs");
            System.out.println("5. Revenire la meniul principal");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    // Creare produs
                    System.out.println("Introduceti numele produsului:");
                    String nume = scanner.nextLine();
                    System.out.println("Introduceti pretul produsului:");
                    double pret = scanner.nextDouble();
                    Produs produs = new Produs(nume, pret);
                    produsService.createProdus(produs);
                    AuditService.getSingleInstance().writeAuditLog("creare_produs");
                }
                case 2 -> {
                    // Citire produs
                    System.out.println("Introduceti ID-ul produsului pe care doriti sa il cititi:");
                    int id = scanner.nextInt();
                    Produs produsRead = produsService.readProdus(id);
                    if (produsRead != null) {
                        System.out.println(produsRead);
                        AuditService.getSingleInstance().writeAuditLog("citire_produs");
                    }
                }
                case 3 -> {
                    // Actualizare produs
                    System.out.println("Introduceti ID-ul produsului pe care doriti sa il actualizati:");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Produs produsToUpdate = produsService.readProdus(idToUpdate);
                    if (produsToUpdate != null) {
                        System.out.println("Introduceti noul nume al produsului:");
                        String newNume = scanner.nextLine();
                        System.out.println("Introduceti noul pret al produsului:");
                        double newPret = scanner.nextDouble();
                        produsToUpdate.setNume(newNume);
                        produsToUpdate.setPret(newPret);
                        produsService.updateProdus(produsToUpdate);
                        AuditService.getSingleInstance().writeAuditLog("actualizare_produs");
                    }
                }
                case 4 -> {
                    // Stergere produs
                    System.out.println("Introduceti ID-ul produsului pe care doriti sa il stergeti:");
                    int idToDelete = scanner.nextInt();
                    produsService.deleteProdus(idToDelete);
                    AuditService.getSingleInstance().writeAuditLog("stergere_produs");
                }
                case 5 -> continueLoop = false;
                default -> System.out.println("Optiune invalida. Va rugam sa alegeti din nou.");
            }
        }
    }
}


