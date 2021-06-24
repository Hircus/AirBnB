import java.time.LocalDate;
import java.util.UUID;

public class Abitazione
{
    private UUID id;
    private float prezzo;
    private String nome;
    private String indirizzo;
    private int numeroPostiLetto;
    private int piano;
    private int numeroLocali;
    private LocalDate inizioDisponibilita;
    private LocalDate fineDisponibilita;

    /*** CONSTRUCTOR ***/

    public Abitazione(float prezzo, String nome, String indirizzo, int numeroPostiLetto, int piano, int numeroLocali, LocalDate inizioDisponibilita, LocalDate fineDisponibilita)
    {
        setPrezzo(prezzo);
        setNome(nome);
        setIndirizzo(indirizzo);
        setNumeroPostiLetto(numeroPostiLetto);
        setPiano(piano);
        setNumeroLocali(numeroLocali);
        setInizioDisponibilita(inizioDisponibilita);
        setFineDisponibilita(fineDisponibilita);
        setId(UUID.randomUUID());
    }

    /*** SETTERS AND GETTERS ***/
    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public float getPrezzo()
    {
        return prezzo;
    }

    public void setPrezzo(float prezzo)
    {
        this.prezzo = prezzo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getIndirizzo()
    {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo)
    {
        this.indirizzo = indirizzo;
    }

    public int getNumeroPostiLetto()
    {
        return numeroPostiLetto;
    }

    public void setNumeroPostiLetto(int numeroPostiLetto)
    {
        this.numeroPostiLetto = numeroPostiLetto;
    }

    public int getPiano()
    {
        return piano;
    }

    public void setPiano(int piano)
    {
        this.piano = piano;
    }

    public int getNumeroLocali()
    {
        return numeroLocali;
    }

    public void setNumeroLocali(int numeroLocali)
    {
        this.numeroLocali = numeroLocali;
    }

    public LocalDate getInizioDisponibilita()
    {
        return inizioDisponibilita;
    }

    public void setInizioDisponibilita(LocalDate inizioDisponibilita)
    {
        this.inizioDisponibilita = inizioDisponibilita;
    }

    public LocalDate getFineDisponibilita()
    {
        return fineDisponibilita;
    }

    public void setFineDisponibilita(LocalDate fineDisponibilita)
    {
        this.fineDisponibilita = fineDisponibilita;
    }

}
