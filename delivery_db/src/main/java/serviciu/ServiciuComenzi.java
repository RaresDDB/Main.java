package serviciu;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

public class ServiciuComenzi {
    private Map<Integer, Utilizator> listaUtilizatori;
    private Map<Integer, Comanda> listaComenzi;
    private Set<Local> listaLocaluri;
    private List<Plata> listaPlati;
    private List<Sofer> soferi;

    public ServiciuComenzi() {
        listaUtilizatori = new HashMap<>();
        listaComenzi = new HashMap<>();
        listaLocaluri = new TreeSet<>(Comparator.comparing(Local::getNume));
        listaPlati = new ArrayList<>();
        this.soferi = new ArrayList<>();
    }

    public void adaugaUtilizator(Utilizator utilizator) {
        listaUtilizatori.put(utilizator.getId(), utilizator);
    }

    public void adaugaComanda(Comanda comanda) {
        listaComenzi.put(comanda.getId(), comanda);
    }

    public void adaugaLocal(Local local) {
        listaLocaluri.add(local);
    }

    public void adaugaSofer(Sofer sofer) {
        this.soferi.add(sofer);
    }

    // interogare 1: cautare comanda dupa id
    public Comanda cautaComandaDupaId(int id) {
        return listaComenzi.get(id);
    }

    // interogare 2: cautare comenzi dintr-o anumita perioada
    public List<Comanda> cautaComenziDintrOAnumitaPerioada(Date dataInceput, Date dataSfarsit) {
        return listaComenzi.values().stream()
                .filter(comanda -> comanda.getData().after(dataInceput) && comanda.getData().before(dataSfarsit))
                .collect(Collectors.toList());
    }

    // interogare 3: calculare valoare totala a comenzilor plasate de un utilizator
    public double calculeazaValoareTotalaComenziUtilizator(Utilizator utilizator) {
        return listaComenzi.values().stream()
                .filter(comanda -> comanda.getUtilizator().equals(utilizator) && comanda.isEfectuata())
                .mapToDouble(Comanda::getValoareTotala)
                .sum();
    }

    // interogare 4: cautare local dupa adresa
    public Local cautaLocalDupaAdresa(String adresa) {
        for (Local local : listaLocaluri) {
            if (local.getAdresa().equals(adresa)) {
                return local;
            }
        }
        return null;
    }

    // interogare 5: calculare valoare totala a comenzilor plasate la un anumit local
    public double calculeazaValoareTotalaComenziLocal(Local local) {
        return listaComenzi.values().stream()
                .filter(comanda -> comanda.getLocal().equals(local) && comanda.isEfectuata())
                .mapToDouble(Comanda::getValoareTotala)
                .sum();
    }

    // interogare 6: cautare produs dupa nume si pret maxim
    public Produs cautaProdusDupaNumeSiPretMaxim(String nume, double pretMaxim) {
        return listaLocaluri.stream()
                .flatMap(local -> local.getMeniu().getListaProduse().stream())
                .filter(produs -> produs.getNume().equals(nume) && produs.getPret() <= pretMaxim)
                .findFirst()
                .orElse(null);
    }

    // interogare 7: cautare comenzi neefectuate
    public List<Comanda> cautaComenziNeefectuate() {
        return listaComenzi.values().stream()
                .filter(comanda -> !comanda.isEfectuata())
                .collect(Collectors.toList());
    }

    // interogare 8: calculare valoare totala a platilor efectuate intr-o anumita zi
    public double calculeazaValoareTotalaPlatiZi(Date data) {
        return listaPlati.stream()
                .filter(plata -> plata.getData().equals(data))
                .mapToDouble(Plata::getValoare)
                .sum();
    }

    // interogare 9: cautare produse comandate de un utilizator la un anumit local
    public List<Produs> cautaProduseComandateDeUtilizatorLaLocal(Utilizator utilizator, Local local) {
        return listaComenzi.values().stream()
                .filter(comanda -> comanda.getUtilizator().equals(utilizator) && comanda.getLocal().equals(local) && comanda.isEfectuata())
                .flatMap(comanda -> comanda.getListaProduse().stream())
                .collect(Collectors.toList());
    }

    // interogare 10: cautare utilizatori care au plasat cel putin o comanda
    public List<Utilizator> cautaUtilizatoriCuComenziPlasate() {
        return listaComenzi.values().stream()
                .filter(Comanda::isEfectuata)
                .map(Comanda::getUtilizator)
                .distinct()
                .collect(Collectors.toList());
    }

    // interogare 11: gasirea soferului cu cele mai multe comenzi finalizate
    public Sofer gasesteSoferCuCeleMaiMulteComenziFinalizate() {
        return soferi.stream()
                .max(Comparator.comparingInt(sofer -> (int) sofer.getComenzi().stream()
                        .filter(Comanda::isEfectuata)
                        .count()))
                .orElse(null);
    }

    // interogare 12: gasirea utilizatorilor cu carduri care expira intr-o anumita luna si an
    public List<Utilizator> cautaUtilizatoriCuCardExpiratInLunaSiAn(int luna, int an) {
        return listaUtilizatori.values().stream()
                .filter(utilizator -> {
                    String[] dataExpirare = utilizator.getCard().getDataExpirare().split("/");
                    int lunaExpirare = Integer.parseInt(dataExpirare[0]);
                    int anExpirare = Integer.parseInt(dataExpirare[1]);
                    return lunaExpirare == luna && anExpirare == an;
                })
                .collect(Collectors.toList());
    }
}
