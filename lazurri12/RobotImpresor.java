public class RobotImpresor extends Robot {
    private static final Object mesa = new Object();

    public RobotImpresor(int trabajosPorHacer) {
        super(trabajosPorHacer);
    }

    @Override
    protected void realizarTrabajo() {
        synchronized (mesa) {
            System.out.println("Robot Impresor está imprimiendo un adorno.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Robot Impresor ha terminado de imprimir un adorno.");
        }
    }
}
