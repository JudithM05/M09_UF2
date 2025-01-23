import java.util.Random;

public class Soci implements Runnable {
    private static final float APORTACIO = 10f;
    private static final int ESPERA_MAX = 100; // Temps màxim d'espera
    private static final int MAX_ANY = 10; // Nombre màxim d'anys
    private Compte compte;

    public Soci() {
        this.compte = Compte.getInstancia();
    }

    public Compte getCompte() {
        return compte;
    }

    @Override
    public void run() {
        Random random = new Random();

        //Iterar anys i mesos
        for (int any = 0; any < MAX_ANY; any++) {
            for (int mes = 1; mes <= 12; mes++) {
                if (mes % 2 == 0) {
                    compte.ingressar(APORTACIO);
                } else {
                    compte.retirar(APORTACIO);
                }

                // Espera aleatòria
                try {
                    Thread.sleep(random.nextInt(ESPERA_MAX));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
