public class Filosof extends Thread {
    private Forquilla forquillaEsquerra; // Forquilla esquerra
    private Forquilla forquillaDreta; // Forquilla dreta
    private int gana; // Comptador de la gana del filòsof
    private int id; // Identificador del filòsof

    // Constructor
    public Filosof(int numComensal, Forquilla esquerra, Forquilla dreta) {
        super("comensal" + numComensal);
        this.id = numComensal;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
        this.gana = 0; // Inicialment, el filòsof no té gana
    }

    // Mètode per agafar la forquilla esquerra
    private void agafarForquillaEsquerra() throws InterruptedException {
        forquillaEsquerra.agafar(id);
    }

    // Mètode per agafar la forquilla dreta
    private void agafarForquillaDreta() throws InterruptedException {
        forquillaDreta.agafar(id);
    }

    // Mètode per deixar les forquilles
    private void deixarForquilles() {
        forquillaEsquerra.deixar(id);
        forquillaDreta.deixar(id);
    }

    // Mètode per agafar les forquilles en ordre i gestionar espera
    private void agafarForquilles() throws InterruptedException {
        while (true) {
            agafarForquillaEsquerra(); // Intenta agafar la forquilla esquerra
            Thread.sleep(50); // Breu pausa per evitar bloquejos
            if (forquillaDreta.getPropietari() == -1) {
                agafarForquillaDreta(); // Intenta agafar la dreta
                return; // Si aconsegueix totes dues, surt del bucle
            } else {
                forquillaEsquerra.deixar(id); // Allibera l'esquerra si la dreta està ocupada
            }
            esperar(); // Espera abans de reintentar
        }
    }

    // Mètode perquè el filòsof intenti menjar
    private void menjar() throws InterruptedException {
        gana++; // Incrementa la gana quan menja
        System.out.println("Filòsof " + getName() + " (" + id + ") menja. Nivell de gana: " + gana);
        Thread.sleep((long) (1000 + Math.random() * 1000)); // Menja entre 1s i 2s
    }

    // Mètode perquè el filòsof pensi
    private void pensar() throws InterruptedException {
        System.out.println("Filòsof " + getName() + " (" + id + ") pensant");
        Thread.sleep((long) (1000 + Math.random() * 1000)); // Pensa entre 1s i 2s
    }

    // Mètode per esperar un temps abans de reintentar agafar les forquilles
    private void esperar() throws InterruptedException {
        Thread.sleep((long) (500 + Math.random() * 500)); // Espera entre 0.5s i 1s
    }

    // Mètode d'execució del filòsof
    @Override
    public void run() {
        try {
            while (true) {
                pensar(); // El filòsof pensa
                agafarForquilles(); // Intenta agafar les forquilles
                menjar(); // Menja
                deixarForquilles(); // Deixa les forquilles
            }
        } catch (InterruptedException e) {
            System.out.println("Filòsof " + getName() + " (" + id + ") ha estat interromput.");
        }
    }
}
