public class Associacio {
    private static final int NUM_SOCIS = 1000;
    private Soci[] socis;

    public Associacio() {
        socis = new Soci[NUM_SOCIS];
        for (int i = 0; i < NUM_SOCIS; i++) {
            socis[i] = new Soci();
        }
    }

    // Inicia el compte i executa els socis
    public void iniciaCompteTempsSocis() {
        Thread[] fils = new Thread[NUM_SOCIS];

        for (int i = 0; i < NUM_SOCIS; i++) {
            fils[i] = new Thread(socis[i]);
            fils[i].start();
        }

        // Espera que tots els fils acabin
        for (int i = 0; i < NUM_SOCIS; i++) {
            try {
                fils[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Mostra el saldo final del compte
    public void mostraBalancComptes() {
        System.out.printf("Saldo: %.2f%n", Compte.getInstancia().getSaldo());
    }

    public static void main(String[] args) {
        Associacio associacio = new Associacio();
        associacio.iniciaCompteTempsSocis();
        associacio.mostraBalancComptes();
    }
}
