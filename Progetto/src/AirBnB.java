import exceptions.UtenteNotFoundException;

import exceptions.UtenteNotFoundException;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AirBnB {
    HashMap<UUID, HashSet<Abitazione>> host_setAbitazioni;
    HashMap<UUID, TreeSet<Prenotazione>> utente_setPrenotazioni;
    HashMap<Abitazione, TreeSet<Prenotazione>> abitazione_setPrenotazioni;
    HashSet<Utente> utenti;

    // DA QUI INIZIA IL CODICE DI ANTONIO
    public Set<UtenteHost> getAllSuperHosts(){
        Set<UtenteHost> superHosts = new HashSet<>();
        for (Utente utente : utenti) {
            if (utente instanceof UtenteHost host){
                Set<Abitazione> abitazioniHost = host_setAbitazioni.get(host.getHostID());
                int prenotazioni = 0;
                for (Abitazione abitazione : abitazioniHost) {
                    prenotazioni += abitazione_setPrenotazioni.get(abitazione).size();
                }
                if (prenotazioni>=0) {
                    host.setSuper(true);
                    superHosts.add(host);
                }
            }
        }
        return superHosts;
    }

    public Prenotazione getLastPrenotazione(UUID utenteID) throws UtenteNotFoundException {
        if(utenteID == null)
            throw new UtenteNotFoundException("L'utente inserito non esiste!");

        return utente_setPrenotazioni.get(utenteID).last();
    }

    public HashSet<Abitazione> getAbitazione (UUID utenteHostId)
    {
        return host_setAbitazioni.get(utenteHostId);
    }

}
