import exceptions.UtenteNotFoundException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AirBnB
{
    Map<UtenteHost, HashSet<Abitazione>> host_setAbitazioni;
    Map<Utente, TreeSet<Prenotazione>> utente_setPrenotazioni;
    Map<Abitazione, TreeSet<Prenotazione>> abitazione_setPrenotazioni;
    Map<UUID, Utente> utenti;
    Map<UUID, Abitazione> abitazioni;

    public AirBnB()
    {
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
    public void addUtente(Utente u)
    {
        if (u == null)
            throw new IllegalArgumentException("L'utente inserito è null.");

        utenti.put(u.getId(), u);
    }

    /**
     * Aggiunge un'abitazione appartentente ad un utente host.
     *
     * @param host       l'utente host al quale appartiene l'abitazione.
     * @param abitazione l'abitazione appartenente all'utente host.
     */
    public void addHostAbitazioni(UUID host, Abitazione abitazione)
    {
        if (utenti.get(host) instanceof UtenteHost userHost)
        {
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
    public void addUtentePrenotazione(UUID idUtente, Prenotazione prenotazione)
    {
        TreeSet<Prenotazione> tree = new TreeSet<>((o1, o2) ->
        {
            if (o1.getDataInizio().isAfter(o2.getDataInizio())) return -1;
            else if (o1.getDataInizio().isBefore(o2.getDataInizio())) return 1;
            else return 0;
        });
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
    public void addAbitPrenotazione(Abitazione abitazione, Prenotazione prenotazione)
    {
        TreeSet<Prenotazione> tree = new TreeSet<>((o1, o2) ->
        {
            if (o1.getDataInizio().isAfter(o2.getDataInizio())) return -1;
            else if (o1.getDataInizio().isBefore(o2.getDataInizio())) return 1;
            else return 0;
        });
        if (!abitazione_setPrenotazioni.containsKey(abitazione)) abitazione_setPrenotazioni.put(abitazione, tree);
        abitazione_setPrenotazioni.get(abitazione).add(prenotazione);
    }

    /**
     * Ritorna tutti i super host (i.e. un utente host con più di 100 prenotazioni ricevute).
     *
     * @return un HashSet contenente tutti i super host.
     */
    public Set<UtenteHost> getAllSuperHosts()
    {
        Set<UtenteHost> superHosts = new HashSet<>();

        for (Utente utente : utenti.values())
        {
            if (utente instanceof UtenteHost host)
            {
                if (host.isSuper()) superHosts.add(host);
                Set<Abitazione> abitazioniHost = host_setAbitazioni.get(host);

                if (abitazioniHost == null)
                {
                    System.out.println("Non ci sono superHost");
                    continue;
                }
                int prenotazioni = 0;
                for (Abitazione abitazione : abitazioniHost)
                {
                    prenotazioni += abitazione_setPrenotazioni.get(abitazione).size();
                }
                if (prenotazioni >= 3)
                {
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
    public Prenotazione getLastPrenotazione(UUID utenteID) throws UtenteNotFoundException
    {
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
    public Abitazione getLastMonthMostPopularAbitazione()
    {
        Abitazione a = new Abitazione();
        long period;
        int maxCounter = 0;

        for (Map.Entry<Abitazione, TreeSet<Prenotazione>> entry : abitazione_setPrenotazioni.entrySet())
        {
            int abitazioneCounter = 0;

            for (Prenotazione p : entry.getValue())
                if (ChronoUnit.DAYS.between(LocalDate.now(), p.getDataInizio()) < 30)
                    abitazioneCounter++;

            if (abitazioneCounter > maxCounter)
                a = entry.getKey();
        }

        return a;
    }

    /**
     * Ritorna l'utente host con più prenotazioni nell'ultimo mese.
     *
     * @return un riferimento dell'utente host con più prenotazioni nell'ultimo mese.
     */
    public UtenteHost getLastMonthMostPopularHost()
    {
        UtenteHost uh = new UtenteHost();
        long period;
        int maxCounter = 0;

        for (Map.Entry<UtenteHost, HashSet<Abitazione>> entry : host_setAbitazioni.entrySet())
        {
            int counterHost = 0;

            for (Abitazione a : entry.getValue())
            {
                for (Prenotazione p : abitazione_setPrenotazioni.get(a))
                {
                    if (ChronoUnit.DAYS.between(LocalDate.now(), p.getDataInizio()) < 30)
                        counterHost++;
                }
            }

            if (counterHost > maxCounter)
                uh = entry.getKey();
        }

        return uh;
    }

    /**
     * Metodo che rimedia tutte le abitazioni inserite nel sistema da un certo utente
     *
     * @param utenteHostId l'utente per il quale si vogliono vedere le prenotazioni
     * @return un HashSet di abitazioni
     */
    public HashSet<Abitazione> getAbitazione(UUID utenteHostId)
    {
        if (utenti.get(utenteHostId) instanceof UtenteHost utenteHost)
        {
            return host_setAbitazioni.get(utenteHost);
        } else return null;
    }

    public int mediaPostiLetto()
    {
        int nAbitazioni = 0;
        int postiLetto = 0;
        for (HashSet<Abitazione> abitazioni : host_setAbitazioni.values())
        {
            nAbitazioni += abitazioni.size();
            for (Abitazione abitazione : abitazioni)
            {
                postiLetto += abitazione.getNumeroPostiLetto();
            }
        }
        if (nAbitazioni == 0)
        {
            System.out.println("Non ci sono abitazioni");
            return nAbitazioni;
        }
        return postiLetto / nAbitazioni;
    }

    /**
     * Metodo che calcola il numero di giorni che ogni utente ha prenotato nell'ultimo mese e restituisce i 5 utenti con il valore più alto
     *
     * @return un TreeSet di Utenti contenente i migliori 5
     */
    public TreeSet<Utente> get5TopUtenti()
    {
        TreeMap<Long, HashSet<Utente>> giorniPrenotazione = new TreeMap<>();
        giorniPrenotazioneBuild(giorniPrenotazione);
        return topUtentiBuild(giorniPrenotazione);
    }


    /**
     * Metodo che si trova i 5 utenti con il maggior numero di giorni prenotati
     *
     * @param giorniPrenotazione TreeMap della forma <LONG,HASHSET<UTENTE>> che contiene per ogni chiave LONG un lista
     *                           di utenti che hanno quel valore (LONG sta per il numero di giorni prenotati)
     * @return un HashSet<Utente> grande massimo 5
     */
    public TreeSet<Utente> topUtentiBuild(TreeMap<Long, HashSet<Utente>> giorniPrenotazione)
    {
        int counter = 0;
        TreeSet<Utente> top5 = new TreeSet<>();
        for (Long key : giorniPrenotazione.descendingKeySet())
        {
            for (Utente utente : giorniPrenotazione.get(key))
            {
                top5.add(utente);
                counter++;
                if (counter == 5) return top5;
            }
        }
        return top5;
    }

    /**
     * Metodo che costruisce un TreeMap della forma <LONG,HASHSET<UTENTE>> dove LONG è il numero di giorni prenotati da
     * un certo UTENTE
     *
     * @param giorniPrenotazione il TreeMap da costruire
     */
    public void giorniPrenotazioneBuild(TreeMap<Long, HashSet<Utente>> giorniPrenotazione)
    {
        long giorni = 0;

        for (UUID key : utenti.keySet())
        {
            for (Prenotazione prenotazione : utente_setPrenotazioni.get(utente_setPrenotazioni.get(key)))
            {
                if (ChronoUnit.DAYS.between(LocalDate.now().minusMonths(1), prenotazione.getDataInizio()) < 31)
                {
                    giorni += calcolaGiorni(prenotazione.getDataInizio(), prenotazione.getDataFine());
                }
            }
            if (!giorniPrenotazione.containsKey(giorni))
            {
                giorniPrenotazione.put(giorni, new HashSet<>());
                giorniPrenotazione.get(giorni).add(utenti.get(key));
            }
        }
    }

    /**
     * Metodo che calcola la differenza in giorni date due LOCALDATE in input
     *
     * @param inizio data di inizio
     * @param fine   data di fine
     * @return numero di giorni (long)
     */

    public long calcolaGiorni(LocalDate inizio, LocalDate fine)
    {
        return ChronoUnit.DAYS.between(inizio, fine);
    }

}
