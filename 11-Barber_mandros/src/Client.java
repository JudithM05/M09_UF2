
public class Client {
    private String nom;

    public Client(int id) {
        this.nom = "Client-" + id;
    }

    public void tallarseElCabell() {
        System.out.println("Tallant cabell a " + getNom());
        try {
            Thread.sleep((long) (900 + Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return nom;
    }
}
