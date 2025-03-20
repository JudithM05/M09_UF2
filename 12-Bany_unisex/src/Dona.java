class Dona extends Thread {
    private final String nom;
    private final BanyUnisex lavabo;

    public Dona(String nom, BanyUnisex lavabo) {
        this.nom = nom;
        this.lavabo = lavabo;
    }

    public void run() {
        try {
            lavabo.entraDona(nom);
            utilitzaLavabo();
            lavabo.surtDona(nom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void utilitzaLavabo() {
        try {
            Thread.sleep((int) (Math.random() * 1000) + 2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}