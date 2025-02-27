import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
    // Llistes per emmagatzemar tabac, llumins i papers
    private List<Tabac> tabacs = new ArrayList<>();
    private List<Llumi> llumins = new ArrayList<>();
    private List<Paper> papers = new ArrayList<>();
    // Variable per indicar si l'estanc està obert o tancat
    private boolean estancObert;

    // Constructor de la classe Estanc
    public Estanc() {
        estancObert = true;
    }

    // Mètode per afegir un nou subministrament a l'estanc
    public synchronized void nouSubministrament() {
        if (!estancObert) return;

        Random rand = new Random();
        int opcio = rand.nextInt(3);

        switch (opcio) {
            case 0:
                addTabac(new Tabac());
                System.out.println("Afegint tabac");
                break;
            case 1:
                addLlumi(new Llumi());
                System.out.println("Afegint Llumí");
                break;
            case 2:
                addPaper(new Paper());
                System.out.println("Afegint Paper");
                break;
        }
        notifyAll();
    }

    // Mètode per afegir tabac a la llista
    public synchronized void addTabac(Tabac tabac) {
        tabacs.add(tabac); // Afegeix tabac a la llista
        notifyAll();
    }

    // Mètode per afegir llumins a la llista
    public synchronized void addLlumi(Llumi llumi) {
        llumins.add(llumi);
        notifyAll();
    }

    // Mètode per afegir paper a la llista
    public synchronized void addPaper(Paper paper) {
        papers.add(paper);
        notifyAll(); // 
    }

    // Mètode per vendre tabac
    public synchronized Tabac venTabac() {
        while (tabacs.isEmpty() && estancObert) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return estancObert ? tabacs.remove(tabacs.size() - 1) : null;
    }

    // Mètode per vendre llumins
    public synchronized Llumi venLlumi() {
        while (llumins.isEmpty() && estancObert) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return estancObert ? llumins.remove(llumins.size() - 1) : null;
    }

    // Mètode per vendre paper
    public synchronized Paper venPaper() {
        while (papers.isEmpty() && estancObert) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return estancObert ? papers.remove(papers.size() - 1) : null;
    }

    // Mètode per tancar l'estanc
    public synchronized void tancarEstanc() {
        estancObert = false;
        notifyAll();
    }

    // Mètode principal d'execució de l'estanc
    public void executar() {
        System.out.println("Estanc obert");
        while (estancObert) {
            nouSubministrament();
            try {
                Thread.sleep(500 + new Random().nextInt(1001));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}