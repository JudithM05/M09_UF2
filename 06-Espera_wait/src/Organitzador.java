public class Organitzador {
    public static void main(String[] args) {
        Esdeveniment esdeveniment = new Esdeveniment(5); // Evento con 5 plazas
        Assistent[] assistents = new Assistent[10];

        // Crear i iniciar els assistents
        for (int i = 0; i < assistents.length; i++) {
            assistents[i] = new Assistent("Assistent-" + i, esdeveniment);
            assistents[i].start(); // Iniciar el fil de l'assistent
        }
    }
}