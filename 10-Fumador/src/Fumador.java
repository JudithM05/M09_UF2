import java.util.Random;

public class Fumador extends Thread {
    // Variables de la classe
    private Estanc estanc;
    private int id;
    private Llumi llumi;
    private Paper paper;
    private Tabac tabac;
    private int fumades;

    // Constructor de la classe Fumador
    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
        this.fumades = 0;
    }

    // Mètode per simular que el fumador fuma
    public void fuma() {
        if (tabac != null && llumi != null && paper != null) {
            System.out.println("Fumador " + id + " fumant");
            try {
                Thread.sleep(500 + new Random().nextInt(501));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            tabac = null;
            llumi = null;
            paper = null;
            fumades++;
            System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
        }
    }

    // Mètode per comprar tabac
    public void compraTabac() {
        synchronized (estanc) {
            tabac = estanc.venTabac();
            if (tabac != null) {
                System.out.println("Fumador " + id + " comprant Tabac");
            }
        }
    }

    // Mètode per comprar paper
    public void compraPaper() {
        synchronized (estanc) {
            paper = estanc.venPaper();
            if (paper != null) {
                System.out.println("Fumador " + id + " comprant Paper");
            }
        }
    }

    // Mètode per comprar llumins
    public void compraLlumi() {
        synchronized (estanc) {
            llumi = estanc.venLlumi();
            if (llumi != null) {
                System.out.println("Fumador " + id + " comprant Llumí");
            }
        }
    }

    // Mètode principal d'execució del fil (fumador)
    @Override
    public void run() {
        while (fumades < 3) {
            compraTabac();
            compraPaper();
            compraLlumi();
            fuma();
        }
    }
}