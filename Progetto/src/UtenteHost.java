import java.util.UUID;

public class UtenteHost extends Utente{
    private final UUID hostID = UUID.randomUUID();
    private boolean isSuper;

    public UtenteHost(String nome, String cognome, String email) { super(nome, cognome, email); }

    public UUID getHostID() { return hostID; }

    public boolean isSuper() { return isSuper; }

    public void setSuper(boolean aSuper) { isSuper = aSuper; }
}
