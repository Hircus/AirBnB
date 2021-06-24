import java.time.LocalDate;
import java.util.UUID;

public class Prenotazione {

    private UUID id;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    public Prenotazione (LocalDate dataInizio, LocalDate dataFine){
        this.id = UUID.randomUUID();
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

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
