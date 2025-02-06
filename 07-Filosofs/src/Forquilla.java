public class Forquilla {
    private int numero;
    private boolean enUs;

    // Constructor
    public Forquilla(int numero) {
        this.numero = numero;
        this.enUs = false; // Inicialmente no está en uso
    }

    // Getter del número de la forquilla
    public int getNumero() {
        return numero;
    }

    // Setter del número de la forquilla
    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Saber si la forquilla está en ús
    public boolean isEnUs() {
        return enUs;
    }

    // Marcar la forquilla como en ús
    public void agafar() {
        enUs = true;
    }

    // Deixar la forquilla
    public void deixar() {
        enUs = false;
    }
}