import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    private Queue<Client> salaEspera;
    private int maxCadires;
    private Object condBarber;
    private static Barberia barberia;

    public Barberia(int maxCadires) {
        this.maxCadires = maxCadires;
        this.salaEspera = new LinkedList<>();
        this.condBarber = new Object();
    }

    public static Barberia getInstance(int cadires) {
        if (barberia == null) {
            barberia = new Barberia(cadires);
        }
        return barberia;
    }

    public Client seguentClient() {
        while (salaEspera.isEmpty()) {
            synchronized (condBarber) {
                try {
                    condBarber.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return salaEspera.remove();
    }

    public void entrarClient(Client client) {

        if (salaEspera.size() < maxCadires) {
            synchronized (condBarber) {
                salaEspera.add(client);
                System.out.println(client.getNom() + " en espera");
                condBarber.notify();
            }
        } else {
            System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
        }
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            Client client = new Client(i);
            entrarClient(client);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 11; i <= 20; i++) {
            Client client = new Client(i);
            entrarClient(client);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getCondBarber() {
        return condBarber;
    }

    public static void main(String[] args) {
        Barberia barberia = Barberia.getInstance(3);
        Barber barber = new Barber("Pepe", barberia);
        barber.start();
        barberia.start();
    }
}
