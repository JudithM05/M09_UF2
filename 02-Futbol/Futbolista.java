public class Futbolista extends Thread {

    // Crear variables ngols i ntirades
    private int ngols;
    private int ntirades;

    // Constructor on es posen els gols i les tirades a 0
    public Futbolista() {
        this.ngols = 0;
        this.ntirades = 0;
    }

    // Variables finals del número de jugadors, número de tirades i probabilitat
    public static final int NUM_JUGADORS =  11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;

    // Mètode run() on cada jugador realitza NUM_TIRADES
    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (Math.random() < PROBABILITAT) { // Marca un gol según la probabilidad
                ngols++;
            }
        }
    }

    // Getters per obtenir el número de gols i tirades
    public int getNgols() {
        return ngols;
    }

    public int getNtirades() {
        return ntirades;
    }
}
