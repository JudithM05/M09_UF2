public class MainDemoFil {
    public static void main(String[] args) {
        // Capturem el fil actual en execució
        Thread currentThread = Thread.currentThread();
        
        // Mostrem l'informació del fil actual
        System.out.println("MainDemoFil.main:");
        System.out.print("Prioritat -> " + currentThread.getPriority() + ", ");
        System.out.println("Nom -> " + currentThread.getName());
        System.out.println("toString() -> " + currentThread.toString());
    }
}
