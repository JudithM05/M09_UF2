import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private int num;
    ReentrantLock bloqueig = new ReentrantLock();

    // Constructor
    public Forquilla(int num) {
        this.num = num;
    }

    // Getter del número de la forquilla
    public int getNum() {
        return num;
    }

    // Mètode per agafar la forquilla
    public void agafar() throws InterruptedException {
        bloqueig.lock();  
    }

    // Mètode per deixar la forquilla
    public void deixar() {
        bloqueig.unlock();
    }
}
