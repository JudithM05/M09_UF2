public class Filosof extends Thread {
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;
    private int id;
    private long iniciGana;
    private long fiGana;

    // Constructor
    public Filosof(int numComensal, Forquilla esquerra, Forquilla dreta) {
        super("Fil" + numComensal);
        this.id = numComensal;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
        this.gana = 0;
    }

    // Intentar agafar forquilles de manera segura per evitar interbloqueig
    private void agafarForquilles() throws InterruptedException {
        while (true) {
            if (forquillaEsquerra.bloqueig.tryLock()) {
                if (forquillaDreta.bloqueig.tryLock()) {
                    System.out.println(getName() + " ha agafat les forquilles esq(" + forquillaEsquerra.getNum() + ") dreta (" + forquillaDreta.getNum() + ")");
                    return;
                } else {
                    forquillaEsquerra.bloqueig.unlock();
                }
            }
            Thread.sleep((long) (500 + Math.random() * 500));
        }
    }

    // Deixar forquilles en ordre correcte
    private void deixarForquilles() {
        System.out.println(getName() + " ha acabat de menjar");
        forquillaDreta.bloqueig.unlock();
        forquillaEsquerra.bloqueig.unlock();
    }

    // Pensar
    private void pensar() throws InterruptedException {
        iniciGana = System.currentTimeMillis();
        System.out.println(getName() + " pensant");
        Thread.sleep((long) (1000 + Math.random() * 1000));
        fiGana = System.currentTimeMillis();
        gana = (int) ((fiGana - iniciGana) / 1000);
    }

    // Menjar
    private void menjar() throws InterruptedException {
        System.out.println(getName() + " menja amb gana " + gana);
        Thread.sleep((long) (1000 + Math.random() * 1000)); // Entre 1s i 2s
        resetGana(); // Reiniciar la gana después de comer
    }

    // Reset Gana
    private void resetGana() {
        iniciGana = 0;
        fiGana = 0;
        gana = 0;
    }

    // Mètode d'execució
    @Override
    public void run() {
        try {
            while (true) {
                agafarForquilles();
                menjar();
                deixarForquilles();
                pensar();
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " ha estat interromput.");
        }
    }
}
