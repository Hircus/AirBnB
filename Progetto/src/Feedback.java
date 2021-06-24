import java.util.UUID;

public class Feedback {
    private UUID id;
    private String titolo;
    private String testo;
    private int punteggio;

    public Feedback(String titolo, String testo, int punteggio) {
        this.id = UUID.randomUUID();
        this.titolo = titolo;
        this.testo = testo;
        this.punteggio = punteggio;
    }

    /***** GETTERS AND SETTERS ******/
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }
}
