public class Forquilla {
    private static final int LLIURE = -1; // Constant que indica que la forquilla està lliure
    private int numero; // Número de la forquilla
    private int propietari; // Número del filòsof que està utilitzant la forquilla

    public int getPropietari() {
        return propietari;
    }

    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }

    // Constructor
    public Forquilla(int numero) {
        this.numero = numero;
        this.propietari = LLIURE; // Inicialment, la forquilla no té propietari
    }

    // Getter del número de la forquilla
    public int getNumero() {
        return numero;
    }

    // Mètode per agafar la forquilla
    public void agafar(int idFilòsof) throws InterruptedException {
        while (propietari != LLIURE) {
            wait(); // Espera si la forquilla està ocupada
        }
        propietari = idFilòsof; // Agafa la forquilla
        System.out.println("Forquilla " + numero + " agafada pel filòsof " + idFilòsof);
    }

    // Mètode per deixar la forquilla
    public void deixar(int idFilòsof) {
        if (propietari == idFilòsof) {
            propietari = LLIURE; // Allibera la forquilla
            System.out.println("Forquilla " + numero + " deixada pel filòsof " + idFilòsof);
            notifyAll(); // Notifica que la forquilla està lliure
        }
    }
}
