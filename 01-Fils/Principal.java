public class Principal {
    public static void main(String[] args) {

        Fil juan = new Fil("Juan");
        Fil pepe = new Fil("Pepe");

        System.out.println("Termina thread main");

        // Inicialitzar el fil Pepe primer
        pepe.start();

        try {
            // Esperar a que Pepe acabi
            pepe.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Després inicialitzar el fil Juan
        juan.start();

        try {
            // Esperar a que Juan acabi
            juan.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Indicar que els fils Pepe i Juan han acabat
        System.out.println("Termina el fil Pepe");
        System.out.println("Termina el fil Juan");
    }
}
