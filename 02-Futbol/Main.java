public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Array con los nombres de los jugadores
        String[] nombres = {
            "Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", 
            "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé"
        };

        // Validación para que el número de nombres coincida con NUM_JUGADORS
        if (nombres.length != Futbolista.NUM_JUGADORS) {
            System.err.println("El número de nombres no coincide con NUM_JUGADORS");
            return;
        }

        // Crear un array de Futbolistas
        Futbolista[] futbolistes = new Futbolista[Futbolista.NUM_JUGADORS];
        
        // Inicializar los objetos Futbolista
        for (int i = 0; i < futbolistes.length; i++) {
            futbolistes[i] = new Futbolista();
        }

        System.out.println("Inici dels xuts --------------------");

        // Iniciar los hilos
        for (Futbolista f : futbolistes) {
            f.start();
        }

        // Esperar a que todos los hilos terminen
        for (Futbolista f : futbolistes) {
            f.join();
        }

        System.out.println("Fi dels xuts -----------------------");
        System.out.println("--- Estadístiques ------");

        // Mostrar las estadísticas con los nombres de los jugadores
        for (int i = 0; i < futbolistes.length; i++) {
            System.out.println(nombres[i] + " -> " + futbolistes[i].getNgols() + " gols");
        }
    }
}