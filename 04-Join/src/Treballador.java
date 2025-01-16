import java.util.Random;

public class Treballador extends Thread {

    // Variables
    private final float nou_anual_brut; // "nou_anual_brut" que no varia amb els anys
    private final int edat_inici_treball; //edat inicial que no canvia
    private final int edat_fi_treball; //edat final que no canvia
    private int edat_actual; // Inicialitzat a 0
    private float cobrat; // Acumula el total cobrat, inicialitzat a 0.0f
    private final Random rnd; // Generador de nombres aleatoris

    // Constructor
    public Treballador(float nou_anual_brut, int edat_inici_treball, int edat_fi_treball, String nom) {
        super(nom); // Utilitza el nom del Thread (per utilitzar-lo amb getName())
        this.nou_anual_brut = nou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    // Mètode que incrementa el total cobrat amb una dotzena part del sou brut
    public void cobra() {
        float souMensual = nou_anual_brut / 12; // el sou repartit entre els dotze mesos
        cobrat += souMensual;
    }

    // Mètode que paga el 24% del que ha cobrat en el mes
    public void pagaImpostos() {
        float impostos = (nou_anual_brut / 12) * 0.24f; // El 24% del sou mensual
        cobrat -= impostos;
    }

    // Mètode run: Simula el procés de treball
    @Override
    public void run() {
        // Simula des de l'edat d'inici fins a l'edat de finalització
        for (int edat = edat_inici_treball; edat <= edat_fi_treball; edat++) {
            edat_actual = edat; // Actualitza l'edat actual
            cobra(); // Cobra el sou mensual
            pagaImpostos(); // Paga els impostos
            try {
                // Simula el pas del temps amb una pausa
                Thread.sleep(rnd.nextInt(100)); // Entre 0 i 100 ms de pausa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Getter per a «cobrat»
    public float getCobrat() {
        return cobrat;
    }

    // Getter per a «edat_actual»
    public int getEdat() {
        return edat_actual;
    }
}
