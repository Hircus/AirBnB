import exceptions.UtenteNotFoundException;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws UtenteNotFoundException {
        AirBnB a = new AirBnB();

        LocalDate data1= LocalDate.now();
        LocalDate data2= LocalDate.now().plusMonths(1);

        Utente antonio = new Utente("Antonio", "Cossu", "cossu@mail.com");
        Utente emilian = new Utente("Hemilian", "Hrisca", "hrisca@mail.com");
        Utente pierluigi = new Utente("Pierluigi", "Filosa", "filosa@mail.com");
        Utente senghor = new Utente("Senghor", "Njampo", "njampo@mail.com");
        Utente senghorlavendetta = new Utente("Senghor", "Njampo", "njampo@mail.com");
        UtenteHost riccardo = new UtenteHost("Riccardo", "Pozzati", "pozzati@mail.com");
        UtenteHost melvin = new UtenteHost("Melvin", "Massotti", "massotti@mail.com");

        Prenotazione p1 = new Prenotazione(LocalDate.now(), LocalDate.now().plusDays(7));
        Prenotazione p2 = new Prenotazione(LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(2));
        Prenotazione p3 = new Prenotazione(LocalDate.now().minusDays(3), LocalDate.now().plusDays(4));
        Prenotazione p4 = new Prenotazione(LocalDate.now().minusMonths(2), LocalDate.now().plusMonths(3));
        Prenotazione p5 = new Prenotazione(LocalDate.now(), LocalDate.now().plusDays(14));
        Prenotazione p6 = new Prenotazione(LocalDate.now(), LocalDate.now().plusDays(3));
        Prenotazione p7 = new Prenotazione(LocalDate.now().minusDays(3), LocalDate.now().plusDays(9));

        Abitazione a1 = new Abitazione(30, "Casa sul mare", "Via Ceppa 1", 3, 2, 4, data1, data2);
        Abitazione a2 = new Abitazione(50, "Casetta in montagna", "Via Bla 1", 2, 1, 4, data1, data2);
        Abitazione a3 = new Abitazione(70, "B&B in città", "Via Poo 1", 5, 1, 4, data1, data2);

        a.addUtente(senghor);
        a.addUtente(senghorlavendetta);
        a.addUtente(pierluigi);
        a.addUtente(antonio);
        a.addUtente(emilian);
        a.addUtente(melvin);
        a.addUtente(riccardo);

        a.addHostAbitazioni(riccardo.getId(), a1);
        a.addHostAbitazioni(riccardo.getId(), a2);
        a.addHostAbitazioni(melvin.getId(), a3);

        a.addUtentePrenotazione(antonio.getId(), p1);
        a.addUtentePrenotazione(emilian.getId(), p2);
        a.addUtentePrenotazione(emilian.getId(), p3);
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

        System.out.println("\nSTAMPO TUTTI I SUPER HOST");
        System.out.println(a.getAllSuperHosts());

        System.out.println("\nSTAMPO TUTTI GLI UTENTI HOST E LE LORO ABITAZIONI");
        System.out.println(a.printUtentiHostAbitazioni());

        System.out.println("\nSTAMPO TUTTI GLI UTENTI E LE LORO PRENOTAZIONI");
        System.out.println(a.printUtentiPrenotazioni());

        System.out.println("\nSTAMPO TUTTE LE ABITAZIONI E LE LORO PRENOTAZIONI");
        System.out.println(a.printAbitazionePrenotazioni());

        System.out.println("TOP 5 UTENTI CON PIU' GIORNI PRENOTATI ULTIMO MESE ");
        System.out.println(a.get5TopUtenti());

        System.out.println("\nSTAMPO L'ABITAZIONE CON PIU' PRENOTAZIONI NELL'ULTIMO MESE");
        System.out.println(a.getLastMonthMostPopularAbitazione());

        System.out.println("STAMPO L'UTENTE HOST CON PIU' PRENOTAZIONI NELL'ULTIMO MESE");
        System.out.println(a.getLastMonthMostPopularHost());

        System.out.println("\nULTIMA PRENOTAZIONE DI EMILIAN E UTENTE NON ESISTENTE");
        try {
            System.out.println(a.getLastPrenotazione(emilian.getId()));
            System.out.println(a.getLastPrenotazione(UUID.randomUUID()));
        }
        catch (UtenteNotFoundException u){
            System.out.println(u.getMessage());
        }

        System.out.println("\nMedia Posti Letto: " + a.mediaPostiLetto());
    }
}
