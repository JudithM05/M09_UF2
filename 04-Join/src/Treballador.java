import java.util.Random;

public class Treballador extends Thread {

    // Variables
    private final float nou_anual_brut; // "nou_anual_brut" que no varia amb els anys
    private final int edat_inici_treball; // edat inicial que no canvia
    private final int edat_fi_treball; // edat final que no canvia
    private int edat_actual; // Inicialitzat a edat_inici_treball
    private float cobrat; // Acumula el total cobrat, inicialitzat a 0.0f
    private final Random rnd; // Generador de nombres aleatoris

    // Constructor
    public Treballador(float nou_anual_brut, int edat_inici_treball, int edat_fi_treball, String nom) {
        super(nom); // Utilitza el nom del Thread (per utilitzar-lo amb getName())
        this.nou_anual_brut = nou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        this.edat_actual = edat_inici_treball; // Inicialitzem a edat_inici_treball
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
        int mesosTotals = (edat_fi_treball - edat_inici_treball) * 12;
        for (int mes = 0; mes < mesosTotals; mes++) {
            cobra(); // Cobra el sou mensual
            pagaImpostos(); // Paga els impostos
            if ((mes + 1) % 12 == 0) { // Incrementa l'edat cada 12 mesos
                edat_actual++;
            }
            try {
                // Simula el pas del temps amb una pausa
                Thread.sleep(100 + rnd.nextInt(101)); // Entre 100 i 200 ms de pausa
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