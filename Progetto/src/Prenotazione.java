import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Prenotazione {
    private UUID id;
    //public UUID casa;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    public Prenotazione(LocalDate dataInizio, LocalDate dataFine){
        try {
            Thread.sleep(1);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
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

    public void addFeedback(Prenotazione p) {
        if (p != null) System.out.println("Feedback possibile");
        else System.out.println("Non è possibile lasciare un Feedback ");
    }

    @Override
    public String toString() {
        return "\t\t" + "ID: " + this.getId() + "\n" + "\t\t" + "Data Inizio: "
                + this.getDataInizio() + "\n" + "\t\t" + "Data Fine: " + this.getDataFine() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return Objects.equals(id, that.id) ;//&& Objects.equals(dataInizio, that.dataInizio) && Objects.equals(dataFine, that.dataFine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
