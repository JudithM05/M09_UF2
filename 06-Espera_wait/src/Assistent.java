import java.util.Random;

class Assistent extends Thread {
    public Esdeveniment esdeveniment;
    public String nom;
    private final Random random = new Random();

    public Assistent(String nom, Esdeveniment esdeveniment) {
        this.nom = nom;
        this.esdeveniment = esdeveniment;
    }

    /*@Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(1000));
                if (random.nextBoolean()) {
                    esdeveniment.ferReserva(this);
                } else {
                    esdeveniment.cancelaReserva(this);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }*/

    /*//70% de reserva i 30% de cancel·lar
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(1000));
                if (random.nextInt(100) < 70) {
                    esdeveniment.ferReserva(this);
                } else {
                    esdeveniment.cancelaReserva(this);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }*/

    //30% de reserva i 70% de cancel·lar
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(1000));
                if (random.nextInt(100) < 30) {
                    esdeveniment.ferReserva(this);
                } else {
                    esdeveniment.cancelaReserva(this);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}