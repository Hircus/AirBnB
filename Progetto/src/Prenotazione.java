import java.time.LocalDate;
import java.util.UUID;

public class Prenotazione implements Comparable<Prenotazione>{
    private UUID id;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    public Prenotazione (LocalDate dataInizio, LocalDate dataFine){
        this.id = UUID.randomUUID();
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void addFeedback( Prenotazione p){
            if(p!=null){
                System.out.println("Feedback possibile");

            }else
                System.out.println("Non è possibile lasciare un Feedback ");
    }

    @Override
    public int compareTo(Prenotazione o) {
        if (this.getDataInizio().isAfter(o.getDataInizio())) return -1;
        else if (this.getDataInizio().isBefore(o.getDataInizio())) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Prenotazione: " + id;
    }
}
