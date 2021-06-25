import exceptions.UtenteNotFoundException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AirBnB {
    Map<UtenteHost, HashSet<Abitazione>> host_setAbitazioni;
    Map<Utente, TreeSet<Prenotazione>> utente_setPrenotazioni;
    Map<Abitazione, TreeSet<Prenotazione>> abitazione_setPrenotazioni;
    Map<UUID, Utente> utenti;
    Map<UUID, Abitazione> abitazioni;

    public AirBnB() {
        this.host_setAbitazioni = new HashMap<>();
        this.utente_setPrenotazioni = new HashMap<>();
        this.abitazione_setPrenotazioni = new HashMap<>();
        this.utenti = new HashMap<>();
        this.abitazioni = new HashMap<>();
    }

    /**
     * Registra un nuovo utente.
     *
     * @param u l'utente da registrare.
     * @throws IllegalArgumentException se l'utente inserito è null.
     */
    public void addUtente(Utente u) {
        if (u == null)
            throw new IllegalArgumentException("L'utente inserito è null.");

        if (!utenti.containsValue(u)) utenti.put(u.getId(), u);
    }

    /**
     * Aggiunge un'abitazione appartentente ad un utente host.
     *
     * @param host       l'utente host al quale appartiene l'abitazione.
     * @param abitazione l'abitazione appartenente all'utente host.
     */
    public void addHostAbitazioni(UUID host, Abitazione abitazione) {
        if (utenti.get(host) instanceof UtenteHost userHost) {
            if (!host_setAbitazioni.containsKey(userHost))
                host_setAbitazioni.put(userHost, new HashSet<>(Arrays.asList(abitazione)));
            else host_setAbitazioni.get(userHost).add(abitazione);
        }
    }

    /**
     * Aggiunge una prenotazione effettuata da un utente.
     *
     * @param idUtente     l'utente che ha effettuato la prenotazione.
     * @param prenotazione la prenotazione effettuata dall'utente.
     */
    public void addUtentePrenotazione(UUID idUtente, Prenotazione prenotazione) {
        TreeSet<Prenotazione> tree = new TreeSet<>();
        Utente u = utenti.get(idUtente);
        if (!utente_setPrenotazioni.containsKey(u)) utente_setPrenotazioni.put(u, tree);
        utente_setPrenotazioni.get(u).add(prenotazione);
    }

    /**
     * Aggiunge una prenotazione relativa ad un'abitazione.
     *
     * @param abitazione   l'abitazione al quale si riferisce la prenotazione.
     * @param prenotazione .
     */
    public void addAbitPrenotazione(Abitazione abitazione, Prenotazione prenotazione) {
        TreeSet<Prenotazione> tree = new TreeSet<>();
        if (!abitazione_setPrenotazioni.containsKey(abitazione)) abitazione_setPrenotazioni.put(abitazione, tree);
        abitazione_setPrenotazioni.get(abitazione).add(prenotazione);
    }

    /**
     * Ritorna tutti i super host (i.e. un utente host con più di 100 prenotazioni ricevute).
     *
     * @return un HashSet contenente tutti i super host.
     */
    public Set<UtenteHost> getAllSuperHosts() {
        Set<UtenteHost> superHosts = new HashSet<>();

        for (Utente utente : utenti.values()) {
            if (utente instanceof UtenteHost host) {
                if (host.isSuper()) superHosts.add(host);
                Set<Abitazione> abitazioniHost = host_setAbitazioni.get(host);

                if (abitazioniHost == null) {
                    System.out.println("Non ci sono superHost");
                    continue;
                }
                int prenotazioni = 0;
                for (Abitazione abitazione : abitazioniHost) {
                    prenotazioni += abitazione_setPrenotazioni.get(abitazione).size();
                }
                if (prenotazioni >= 3) {
                    host.setSuper(true);
                    superHosts.add(host);
                }
            }
        }
        return superHosts;
    }

    /**
     * Ritorna l'ultima prenotazione effettuata da un dato utente.
     *
     * @param utenteID id dell'utente dal quale si vuole ottenere l'ultima prenotazione da lui effettuata.
     * @return un riferimento dell'ultima prenotazione effettuata dall'utente.
     * @throws UtenteNotFoundException
     */
    public Prenotazione getLastPrenotazione(UUID utenteID) throws UtenteNotFoundException {
        if (utenteID == null)
            throw new IllegalArgumentException("L'utente inserito è null.");

        if (!utente_setPrenotazioni.containsKey(utenti.get(utenteID)))
            throw new UtenteNotFoundException("Utente non trovato.");

        return utente_setPrenotazioni.get(utenti.get(utenteID)).last();
    }


    /**
     * Ritorna l'abitazione più popolare (i.e. quella con più prenotazioni) nell'ultimo mese.
     *
     * @return un riferimento dell'abitazione più popolare.
     */
    public Abitazione getLastMonthMostPopularAbitazione() {
        Abitazione popularA = new Abitazione();
        int maxCounter = 0;

        for (Map.Entry<Abitazione, TreeSet<Prenotazione>> entry : abitazione_setPrenotazioni.entrySet()) {
            int abitazioneCounter = 0;
            for (Prenotazione p : entry.getValue())
                if (ChronoUnit.DAYS.between(LocalDate.now(), p.getDataInizio()) < 30)
                    abitazioneCounter++;

            if (abitazioneCounter > maxCounter)
                maxCounter = abitazioneCounter;
                popularA = entry.getKey();
        }
        return popularA;
    }

    /**
     * Ritorna l'utente host con più prenotazioni nell'ultimo mese.
     *
     * @return un riferimento dell'utente host con più prenotazioni nell'ultimo mese.
     */
    public UtenteHost getLastMonthMostPopularHost() {
        UtenteHost popularH = new UtenteHost();
        int maxCounter = 0;

        for (Map.Entry<UtenteHost, HashSet<Abitazione>> entry : host_setAbitazioni.entrySet()) {
            int counterHost = 0;
            for (Abitazione a : entry.getValue()) {
                for (Prenotazione p : abitazione_setPrenotazioni.get(a)) {
                    if (ChronoUnit.DAYS.between(LocalDate.now(), p.getDataInizio()) < 30)
                        counterHost++;
                }
            }
            if (counterHost > maxCounter)
                maxCounter = counterHost;
                popularH = entry.getKey();
        }
        return popularH;
    }


    public HashSet<Abitazione> getAbitazione(UUID utenteHostId) {
        if (utenti.get(utenteHostId) instanceof UtenteHost utenteHost) {
            return host_setAbitazioni.get(utenteHost);
        } else return null;
    }

    /**
     * @return un intero che indica la media dei posti letti calcolata su tutte le abitazioni
     */
    public int mediaPostiLetto() {
        int nAbitazioni = 0;
        int postiLetto = 0;
        for (HashSet<Abitazione> abitazioni : host_setAbitazioni.values()) {
            nAbitazioni += abitazioni.size();
            for (Abitazione abitazione : abitazioni) {
                postiLetto += abitazione.getNumeroPostiLetto();
            }
        }
        if (nAbitazioni == 0) {
            System.out.println("Non ci sono abitazioni");
            return nAbitazioni;
        }
        return postiLetto / nAbitazioni;
    }

    /**
     * Stampa una mappa contenente tutte le abitazioni degli utenti super host.
     */
    public String printUtentiHostAbitazioni() {
        StringBuilder text = new StringBuilder();
        for (Map.Entry<UtenteHost, HashSet<Abitazione>> e : host_setAbitazioni.entrySet()) {
            text.append("\n").append(e.getKey()).append("\n");
            for (Abitazione a : e.getValue()) {
                text.append("\t").append("Abitazione: ").append("\n");
                text.append(a).append("\n");
            }
        }
        return text.toString();
    }

    /**
     * Stampa una mappa contenente tutte le prenotazioni effettuate dagli utenti.
     */
    public String printUtentiPrenotazioni() {
        StringBuilder text = new StringBuilder();
        for (Map.Entry<Utente, TreeSet<Prenotazione>> e : utente_setPrenotazioni.entrySet()) {
            text.append("\n").append(e.getKey()).append("\n");
            for (Prenotazione p : e.getValue()) {
                text.append("\t").append("Prenotazione: ").append("\n");
                text.append(p).append("\n");
            }
        }
        return text.toString();
    }

    /**
     * Stampa una mappa contenente tutte le prenotazioni di tutte le abitazioni.
     */
    public String printAbitazionePrenotazioni() {
        StringBuilder text = new StringBuilder();
        for (Map.Entry<Abitazione, TreeSet<Prenotazione>> e : abitazione_setPrenotazioni.entrySet()) {
            text.append("\n").append("Abitazione: ");
            text.append("\n").append(e.getKey()).append("\n");
            for (Prenotazione p : e.getValue()) {
                text.append("\t\t").append("Prenotazione: ").append("\n").append(p).append("\n");
            }
        }
        return text.toString();
    }
}
