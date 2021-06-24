import exceptions.UtenteNotFoundException;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) throws UtenteNotFoundException {
        AirBnB a = new AirBnB();

        LocalDate data1= LocalDate.now();
        LocalDate data2= LocalDate.now().plusMonths(1);
        System.out.println((Period.between(data1,data2).getYears()*12+ Period.between(data1,data2).getMonths())*30);
        System.out.println(LocalDate.now().toEpochDay());
        long period = ChronoUnit.DAYS.between(data1, data2);
        System.out.println(period);

        Utente antonio = new Utente("Antonio", "Cossu", "cossu@mail.com");
        Utente hemilian = new Utente("Hemilian", "Hrisca", "hrisca@mail.com");
        Utente pierluigi = new Utente("Pierluigi", "Filosa", "filosa@mail.com");
        Utente senghor = new Utente("Senghor", "Njampo", "njampo@mail.com");
        Utente riccardo = new UtenteHost("Riccardo", "Pozzati", "pozzati@mail.com");
        Utente melvin = new UtenteHost("Melvin", "NonRicordo", "nonricordo@mail.com");

        Prenotazione p1 = new Prenotazione(LocalDate.now(), LocalDate.now().plusDays(7));
        Prenotazione p2 = new Prenotazione(LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(2));
        Prenotazione p3 = new Prenotazione(LocalDate.now().minusDays(3), LocalDate.now().plusDays(4));
        Prenotazione p4 = new Prenotazione(LocalDate.now().minusMonths(2), LocalDate.now().plusMonths(3));
        Prenotazione p5 = new Prenotazione(LocalDate.now(), LocalDate.now().plusDays(14));
        Prenotazione p6 = new Prenotazione(LocalDate.now(), LocalDate.now().plusDays(3));
        Prenotazione p7 = new Prenotazione(LocalDate.now().minusDays(3), LocalDate.now().plusDays(9));

        Abitazione a1 = new Abitazione(30, "Casa sul mare", "Via Ceppa 1", 3, 2, 4, data1, data2);
        Abitazione a2 = new Abitazione(50, "Casetta in montagnia", "Via Bla 1", 1, 1, 4, data1, data2);
        Abitazione a3 = new Abitazione(70, "B&B in città", "Via Poo 1", 1, 1, 4, data1, data2);

        a.addHostAbitazioni(riccardo.getId(), a1);
        a.addHostAbitazioni(riccardo.getId(), a2);
        a.addHostAbitazioni(melvin.getId(), a3);

        a.addUtentePrenotazione(antonio.getId(), p1);
        a.addUtentePrenotazione(hemilian.getId(), p2);
        a.addUtentePrenotazione(hemilian.getId(), p3);
        a.addUtentePrenotazione(pierluigi.getId(), p4);
        a.addUtentePrenotazione(senghor.getId(), p5);
        a.addUtentePrenotazione(senghor.getId(), p6);
        a.addUtentePrenotazione(pierluigi.getId(), p7);

        a.addAbitPrenotazione(a1, p1);
        a.addAbitPrenotazione(a1, p3);
        a.addAbitPrenotazione(a2, p2);
        a.addAbitPrenotazione(a2, p4);
        a.addAbitPrenotazione(a3, p7);
        a.addAbitPrenotazione(a1, p5);
        a.addAbitPrenotazione(a2, p6);
        
        a.addUtenti(antonio);
        a.addUtenti(hemilian);
        a.addUtenti(pierluigi);
        a.addUtenti(senghor);
        a.addUtenti(riccardo);
        a.addUtenti(melvin);
    }
}
