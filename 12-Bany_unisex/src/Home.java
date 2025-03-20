class Home extends Thread {
    private final String nom;
    private final BanyUnisex lavabo;

    public Home(String nom, BanyUnisex lavabo) {
        this.nom = nom;
        this.lavabo = lavabo;
    }

    public void run() {
        try {
            lavabo.entraHome(nom);
            utilitzaLavabo();
            lavabo.surtHome(nom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void utilitzaLavabo() {
        try {
            Thread.sleep((int) (Math.random() * 1000) + 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}