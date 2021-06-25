import java.util.UUID;

public class UtenteHost extends Utente{
    private final UUID hostID;
    private boolean isSuper;

    public UtenteHost(String nome, String cognome, String email) {
        super(nome, cognome, email);
        this.hostID = UUID.randomUUID();
    }

    public UtenteHost() {
        super();
        this.hostID = UUID.randomUUID();
    }

    public UUID getHostID() { return hostID; }

    public boolean isSuper() { return isSuper; }

    public void setSuper(boolean aSuper) { isSuper = aSuper; }
}
