import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class AirBnB {
    HashMap<UtenteHost, HashSet<Abitazione>> host_setAbitazioni;
    HashMap<Utente, TreeSet<Prenotazione>> utente_setPrenotazioni;
    HashMap<Abitazione, TreeSet<Prenotazione>> abitazione_setPrenotazioni;

    public static void main(String[] args) {
        AirBnB a = new AirBnB();
    }
}
