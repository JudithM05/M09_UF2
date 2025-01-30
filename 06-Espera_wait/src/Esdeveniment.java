import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Esdeveniment {
    private final List<Assistent> assistents = new ArrayList<>();
    private final int maxPlaces;
    private int placesDisponibles;

    public Esdeveniment(int maxPlaces) {
        this.maxPlaces = maxPlaces;
        this.placesDisponibles = maxPlaces;
    }

    public synchronized void ferReserva(Assistent assistent) {
        while (placesDisponibles == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        assistents.add(assistent);
        placesDisponibles--;
        System.out.println(assistent.nom + " ha fet una reserva. Places disponibles: " + placesDisponibles);
        notifyAll();
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        if (assistents.remove(assistent)) {
            placesDisponibles++;
            System.out.println(assistent.nom + " ha cancel·lat una reserva. Places disponibles: " + placesDisponibles);
            notifyAll();
        } else {
            System.out.println(assistent.nom + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + placesDisponibles);
        }
    }
}