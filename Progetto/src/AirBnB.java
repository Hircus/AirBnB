import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class AirBnB {
    HashMap<UtenteHost, HashSet<Abitazione>> host_setAbitazioni;
    HashMap<Utente, TreeSet<Prenotazione>> utente_setPrenotazioni;
    HashMap<Abitazione, TreeSet<Prenotazione>> abitazione_setPrenotazioni;

    // DA QUI INIZIA IL CODICE DI ANTONIO
    public Set<UtenteHost> getAllSuperHosts(){
        for (Utente utente : utenti) {
            if (utente instanceof UtenteHost host && host.isSuper()){
                hos
            }
        }
    }

    public static void main(String[] args) {
        AirBnB a = new AirBnB();

        LocalDate data1= LocalDate.EPOCH;
        LocalDate data2= LocalDate.now();
        System.out.println((Period.between(data1,data2).getYears()*12+ Period.between(data1,data2).getMonths())*30);
        System.out.println(LocalDate.now().toEpochDay());
        long p2 = ChronoUnit.DAYS.between(data1, data2);
        System.out.println(p2);
    }
}
