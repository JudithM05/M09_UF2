import java.util.Scanner;
public class Coet {
    Motor[] motors;

    public Coet() {
        // Inicialitza els 4 motors amb les seves IDs
        motors = new Motor[4];
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor(i);
        }
    }

    // Mètode per establir la potència objectiu a tots els motors
    public void passaAPotencia(int p) {
        if (p < 0 || p > 10) {
            System.out.println("Error: la potència ha d'estar entre 0 i 10.");
            return;
        }

        System.out.println("Passant a potència " + p);
        for (Motor motor : motors) {
            motor.setPotenciaObjectiu(p);
        }
    }

    // Mètode per arrencar els motors
    public void arranca() {
        Scanner scanner = new Scanner(System.in);
        int potencia;

        while (true) {
            System.out.print("");
            try {
                potencia = scanner.nextInt();

                if (potencia == 0) {
                    passaAPotencia(0);
                    break;
                }

                passaAPotencia(potencia);
            } catch (Exception e) {
                System.out.println("Error: introdueix un número vàlid.");
                scanner.next(); // Netegem l'entrada
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();
    }
}
