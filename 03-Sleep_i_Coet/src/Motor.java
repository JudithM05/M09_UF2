public class Motor {
    private int potenciaObjectiu = 0;
    private int potenciaActual = 0;
    private boolean enFuncionament = true;
    private int id;

    public Motor(int id) {
        this.id = id;
    }

    public void setPotenciaObjectiu(int p) {
        this.potenciaObjectiu = p;
        ajustarPotencia();
    }
    
    public int getPotenciaActual() {
        return potenciaActual;
    }

    private void ajustarPotencia() {
        //Fa un nou Thread
        new Thread(() -> {
            try {
                //Mentre està en funcionament
                while (enFuncionament && potenciaActual != potenciaObjectiu) {
                    //S'aumenta la potència actual si és menor que la objectiu
                    if (potenciaActual < potenciaObjectiu) {
                        potenciaActual++;
                        System.out.println("Motor " + id + ": Incre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    //Es decrementa la potència actual si és major que la objectiu
                    } else if (potenciaActual > potenciaObjectiu) {
                        potenciaActual--;
                        System.out.println("Motor " + id + ": Decre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    }

                    // Simula l'intèrval aleatori
                    int interval = (int) (Math.random() * 1000) + 1000; // Entre 1000ms i 2000ms
                    Thread.sleep(interval);
                }

                //Si les dues potències són 0 no està en funcionament
                if (potenciaObjectiu == 0 && potenciaActual == 0) {
                    System.out.println("Motor " + id + ": FerRes Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    enFuncionament = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
