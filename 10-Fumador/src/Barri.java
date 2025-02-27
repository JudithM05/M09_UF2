public class Barri extends Thread {
    // Variables de la classe
    private Estanc estanc;
    private Fumador[] fumadors;

    // Constructor de la classe Barri
    public Barri() {
        this.estanc = new Estanc();
        this.fumadors = new Fumador[]{
            new Fumador(estanc, 0),
            new Fumador(estanc, 1),
            new Fumador(estanc, 2)
        };
    }

    // Mètode per iniciar tots els fumadors
    public void iniciaFumadors() {
        for (Fumador fumador : fumadors) {
            fumador.start();
        }
    }

    // Mètode per iniciar l'estanc
    public void iniciaEstanc() {
        new Thread(() -> estanc.executar()).start();
    }

    // Mètode per esperar que els fumadors acabin i tancar l'estanc
    public void esperaIFinalitza() {
        for (Fumador fumador : fumadors) {
            try {
                fumador.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        estanc.tancarEstanc();
        System.out.println("Estanc tancat");
    }

    // Mètode principal (punt d'entrada del programa)
    public static void main(String[] args) {
        Barri barri = new Barri();
        barri.iniciaEstanc();
        barri.iniciaFumadors();
        barri.esperaIFinalitza();
    }
}