class Barber extends Thread {
    private String nom;
    private Barberia barberia;

    public Barber(String nom, Barberia barberia) {
        this.nom = nom;
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            Client client = barberia.seguentClient();
            if (client == null) {
                System.out.println("Ningú en espera");
                dormir();
            } else {
                System.out.println("Li toca al client " + client.getNom());
                client.tallarseElCabell();
            }
        }
    }

    /*private void tallarCabell(Client client) {
        System.out.println("Tallant cabell a " + client.getNom());
        try {
            Thread.sleep((long) (900 + Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    private void dormir() {
        System.out.println("Barber " + nom + " dormint");
        synchronized (barberia.getCondBarber()) {
            try {
                barberia.getCondBarber().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}