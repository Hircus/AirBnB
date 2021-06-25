import exceptions.UtenteNotFoundException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AirBnB {
    Map<UUID, HashSet<Abitazione>> host_setAbitazioni;
    Map<UUID, TreeSet<Prenotazione>> utente_setPrenotazioni;
    Map<Abitazione, TreeSet<Prenotazione>> abitazione_setPrenotazioni;
    Set<Utente> utenti;

    public AirBnB() {
        this.host_setAbitazioni = new HashMap<>();
        this.utente_setPrenotazioni = new HashMap<>();
        this.abitazione_setPrenotazioni = new HashMap<>();
        this.utenti = new HashSet<>();
    }

    /**
    * Registra un nuovo utente.
    * @param u l'utente da registrare.
    * @throws IllegalArgumentException se l'utente inserito è null.
    */
    public void addUtente(Utente u) {
        if(u == null)
            throw new IllegalArgumentException ("L'utente inserito è null.");

        utenti.add(u);
    }

    /**
    * Aggiunge un'abitazione appartentente ad un utente host.
    * @param host l'utente host al quale appartiene l'abitazione.
    * @param abitazione l'abitazione appartenente all'utente host.
    */
    public void addHostAbitazioni(UUID host, Abitazione abitazione){
        if (host_setAbitazioni.containsKey(host)) host_setAbitazioni.get(host).add(abitazione);
        else host_setAbitazioni.put(host, new HashSet<>(Set.of(abitazione)));
    }

    /**
    * Aggiunge una prenotazione effettuata da un utente.
    * @param idUtente l'utente che ha effettuato la prenotazione.
    * @param prenotazione la prenotazione effettuata dall'utente.
    */
    public void addUtentePrenotazione(UUID idUtente, Prenotazione prenotazione){
        TreeSet<Prenotazione> tree = new TreeSet<>((o1, o2) -> {
            if (o1.getDataInizio().isAfter(o2.getDataInizio())) return -1;
            else if (o1.getDataInizio().isBefore(o2.getDataInizio())) return 1;
            else return 0;
        });
        if (!utente_setPrenotazioni.containsKey(idUtente)) utente_setPrenotazioni.put(idUtente, tree);
        utente_setPrenotazioni.get(idUtente).add(prenotazione);
    }

    /**
    * Aggiunge una prenotazione relativa ad un'abitazione.
    * @param abitazione l'abitazione al quale si riferisce la prenotazione.
    * @param prenotazione .
    */
    public void addAbitPrenotazione(Abitazione abitazione, Prenotazione prenotazione){
        TreeSet<Prenotazione> tree = new TreeSet<>((o1, o2) -> {
            if (o1.getDataInizio().isAfter(o2.getDataInizio())) return -1;
            else if (o1.getDataInizio().isBefore(o2.getDataInizio())) return 1;
            else return 0;
        });
        if (!abitazione_setPrenotazioni.containsKey(abitazione)) abitazione_setPrenotazioni.put(abitazione, tree);
        abitazione_setPrenotazioni.get(abitazione).add(prenotazione);
    }

    /**
    * Ritorna tutti i super host (i.e. un utente host con più di 100 prenotazioni ricevute).
    * @return un HashSet contenente tutti i super host.
    */
    public Set<UtenteHost> getAllSuperHosts(){
        Set<UtenteHost> superHosts = new HashSet<>();
        for (Utente utente : utenti) {
            if (utente instanceof UtenteHost host){
                if (host.isSuper()) superHosts.add(host);
                Set<Abitazione> abitazioniHost = host_setAbitazioni.get(host.getHostID());
                int prenotazioni = 0;
                if (abitazioniHost == null) {
                    System.out.println("Non ci sono superHost");
                    break;
                }
                for (Abitazione abitazione : abitazioniHost) {
                    prenotazioni += abitazione_setPrenotazioni.get(abitazione).size();
                }
                if (prenotazioni>=3) {
                    host.setSuper(true);
                    superHosts.add(host);
                }
            }
        }
        return superHosts;
    }

    /**
    * Ritorna l'ultima prenotazione effettuata da un dato utente.
    * @param utenteID id dell'utente dal quale si vuole ottenere l'ultima prenotazione da lui effettuata.
    * @return un riferimento dell'ultima prenotazione effettuata dall'utente.
    * @throws UtenteNotFoundException
    */
    public Prenotazione getLastPrenotazione(UUID utenteID) throws UtenteNotFoundException {
        if(utenteID == null)
            throw new IllegalArgumentException ("L'utente inserito è null.");

        if(!utente_setPrenotazioni.containsKey(utenteID))
            throw new UtenteNotFoundException("Utente non trovato.");

        return utente_setPrenotazioni.get(utenteID).last();
    }


    public HashSet<Abitazione> getAbitazione (UUID utenteHostId)
    {
        return host_setAbitazioni.get(utenteHostId);
    }

}
