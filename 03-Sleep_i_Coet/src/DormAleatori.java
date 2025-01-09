public class DormAleatori extends Thread {
    private String nom;
    private long tempsDeCreacio;

    // Constructor amb el nom i el temps de creació
    public DormAleatori(String nom) {
        this.nom = nom;
        this.tempsDeCreacio = System.currentTimeMillis();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Bucle de 10 iteracions
            try {
                int intervalAleatori = (int) (Math.random() * 1000); // Intèrval aleatori de 0 a 1000
                System.out.printf("%s(%d) a dormir %dms total %d%n", 
                    nom, // Nom del fil
                    i,   // Número de l'iteració
                    intervalAleatori, // Intèrval aleatori
                    (System.currentTimeMillis() - tempsDeCreacio) // Total del temps de la creació
                );
                Thread.sleep(intervalAleatori); // Posa a dormir el fil
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Instanciar els fils
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");

        // Inicialitzar els fils
        joan.start();
        pep.start();

        // Esperar un temps per assegurar-se que Joan(0) i Pep(0) s'han imprès
        try {
            Thread.sleep(50); // Esperar 50ms (ajustable segons necessitats)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Imprimir el missatge de fi de main
        System.out.printf("-- Fi de main -----------%n");
    }
}
