import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    public static final int BANY_BUIT = 0;
    public static final int BANY_AMB_HOMES = 1;
    public static final int BANY_AMB_DONES = 2;

    private int estatActual = BANY_BUIT;
    private int ocupants = 0;
    private static final int CAPACITAT_MAX = 3;
    private Semaphore capacitat = new Semaphore(CAPACITAT_MAX, true);
    private ReentrantLock lockEstat = new ReentrantLock(true);

    public void entraHome(String nom) {
        System.out.println(nom + " vol entrar al bany");
        boolean entered = false;
        while (!entered) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        estatActual = BANY_AMB_HOMES;
                        System.out.println("Home entra al bany. Ocupants: " + ocupants);
                        entered = true;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
        }
    }

    public void entraDona(String nom) {
        System.out.println(nom + " vol entrar al bany");
        boolean entered = false;
        while (!entered) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        estatActual = BANY_AMB_DONES;
                        System.out.println("Dona entra en el bany. Ocupants: " + ocupants);
                        entered = true;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
        }
    }

    public void surtHome(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            System.out.println("Home surt del bany. Ocupants: " + ocupants);
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
            System.out.println(nom + " ha acabat d'usar el bany");
        } finally {
            lockEstat.unlock();
        }
    }

    public void surtDona(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            System.out.println("Dona surt del bany. Ocupants: " + ocupants);
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
            System.out.println(nom + " ha acabat d'usar el bany");
        } finally {
            lockEstat.unlock();
        }
    }

    public static void main(String[] args) {
        BanyUnisex lavabo = new BanyUnisex();

        for (int i = 0; i < 5; i++) {
            new Home("Home-" + i, lavabo).start();
            new Dona("Dona-" + i, lavabo).start();
        }
    }
}