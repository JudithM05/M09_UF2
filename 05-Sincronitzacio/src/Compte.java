public class Compte {
    private float saldo;
    private static Compte instancia;

    public static synchronized Compte getInstancia() {
        if (instancia == null) {
            instancia = new Compte();
        }
        return instancia;
    }

    public synchronized float getSaldo() {
        return saldo;
    }

    public synchronized void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    // Mètodes per ingressar i retirar diners
    public synchronized void ingressar(float quantitat) {
        saldo += quantitat;
    }

    public synchronized void retirar(float quantitat) {
        saldo -= quantitat;
    }
}
