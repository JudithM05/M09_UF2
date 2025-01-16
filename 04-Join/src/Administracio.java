public class Administracio extends Thread {
    private static final int NUM_POBLACIO_ACTIVA = 50;
    private final Treballador[] poblacio_activa;

    // Constructor
    public Administracio() {
        poblacio_activa = new Treballador[NUM_POBLACIO_ACTIVA];
        for (int i = 0; i < NUM_POBLACIO_ACTIVA; i++) {
            //Crea un nou treballador per cada número
            poblacio_activa[i] = new Treballador(25000.0f, 20, 65, "Ciutadà-" + i);
        }
    }

    // Mètode principal per executar els threads i mostrar estadístiques
    public void executa() {
        // Iniciar tots els threads
        for (Treballador treballador : poblacio_activa) {
            treballador.start();
        }

        // Esperar a que tots els threads acabin amb join()
        for (Treballador treballador : poblacio_activa) {
            try {
                treballador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar estadístiques
        for (Treballador treballador : poblacio_activa) {
            System.out.printf("%s -> edat: %d / total: %.2f\n",
                    treballador.getName(), treballador.getEdat(), treballador.getCobrat());
        }
    }

    // Mètode main per iniciar el programa
    public static void main(String[] args) {
        Administracio administracio = new Administracio();
        administracio.executa();
    }
}