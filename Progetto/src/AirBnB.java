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
        for (Utente utente : utenti) {
            if (utente instanceof UtenteHost host && host.isSuper()){
                hos
            }
        }
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
