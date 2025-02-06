public class Filosof implements Runnable {
    private String nom;
    private int gana;
    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;

    // Constructor que inicialitza el nom i les forquilles
    public Filosof(String nom, Forquilla esquerra, Forquilla dreta) {
        this.nom = nom;
        this.gana = 0; // Inicialment sense gana
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
    }

    // Mètode per a que el filòsof intenti menjar
    private void menjar() {
        while (true) {
            if (!forquillaEsquerra.isEnUs() && !forquillaDreta.isEnUs()) {
                forquillaEsquerra.agafar();
                System.out.println("Filòsof: " + nom + " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());
                forquillaDreta.agafar();
                System.out.println("Filòsof: " + nom + " agafa la forquilla dreta " + forquillaDreta.getNumero());
                System.out.println("Filòsof: " + nom + " menja");
                try {
                    Thread.sleep((long) (1000 + Math.random() * 1000)); // Menja entre 1s i 2s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                forquillaEsquerra.deixar();
                forquillaDreta.deixar();
                System.out.println("Filòsof: " + nom + " ha acabat de menjar");
                break;
            } else {
                System.out.println("Filòsof: " + nom + " deixa l'esquerra (" + forquillaEsquerra.getNumero() + ") i espera (dreta ocupada)");
                gana++;
                System.out.println("Filòsof: " + nom + " gana=" + gana);
                try {
                    Thread.sleep((long) (500 + Math.random() * 500)); // Espera entre 0.5s y 1s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Mètode per a que el filòsof pensi
    private void pensar() {
        System.out.println("Filòsof: " + nom + " pensant");
        try {
            Thread.sleep((long) (1000 + Math.random() * 1000)); // Piensa entre 1s y 2s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Mètode d'execució del filòsof
    @Override
    public void run() {
        while (true) {
            pensar();
            menjar();
        }
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
