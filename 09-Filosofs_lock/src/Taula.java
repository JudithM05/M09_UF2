public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;

    // Constructor que inicialitza la taula amb filòsofs i forquilles
    public Taula(int numFilosofs) {
        comensals = new Filosof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];

        // Crear forquilles
        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        // Crear filòsofs i assignar forquilles
        for (int i = 0; i < numFilosofs; i++) {
            Forquilla esquerra = forquilles[i];
            Forquilla dreta = forquilles[(i + 1) % numFilosofs];
            comensals[i] = new Filosof(i, esquerra, dreta);
        }
    }

    // Mètode per mostrar la taula amb els filòsofs i les seves forquilles
    public void showTaula() {
        for (int i = 0; i < comensals.length; i++) {
            System.out.println("Comensal: Fil" + i + " esq:" + forquilles[i].getNum() + 
                               " dret:" + forquilles[(i + 1) % forquilles.length].getNum());
        }
        System.out.println("-----------------------------");
    }

    // Mètode que inicia els filòsofs a la taula
    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            new Thread(filosof).start();
        }
    }

    // Mètode principal per executar el programa
    public static void main(String[] args) {
        int numFilosofs = 4;
        Taula taula = new Taula(numFilosofs);
        taula.showTaula();
        taula.cridarATaula();
    }
}
