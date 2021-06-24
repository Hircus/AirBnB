import java.util.UUID;

public class Utente {
    private final UUID id;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;

    public Utente(String nome, String cognome, String email) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public void addFeedback(Prenotazione p){

    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }

    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getIndirizzo() { return indirizzo; }

    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }

    public UUID getId() { return id; }

    @Override
    public boolean equals(Object obj) { return super.equals(obj) && obj instanceof Utente u && u.id.equals(this.id); }
}
