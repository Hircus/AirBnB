import java.time.LocalDate;
import java.util.UUID;

public class Prenotazione {
public Prenotazione (UUID id, LocalDate dataInizio, LocalDate dataFine){

    this.id = id;
    this.dataInizio = dataInizio;
    this.dataFine = dataFine;

}


    private UUID id;
    private LocalDate dataInizio;
    private LocalDate dataFine;


    public void setId(UUID id) {
        this.id = id;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
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
}

}
