public class RobotPintor extends Robot {
    private static final Object mesa = new Object();

    public RobotPintor(int trabajosPorHacer) {
        super(trabajosPorHacer);
    }

    @Override
    protected void realizarTrabajo() {
        synchronized (mesa) {
            System.out.println("Robot Pintor está pintando un adorno.");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Robot Pintor ha terminado de pintar un adorno.");
        }
    }
}
