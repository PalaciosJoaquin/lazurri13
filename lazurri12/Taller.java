import java.util.ArrayList;
import java.util.List;

public class Taller {
    private final List<Robot> robots;

    public Taller(List<Robot> robots) {
        this.robots = robots;
    }

    public void iniciarSimulacion() {
        for (Robot robot : robots) {
            robot.start();
        }

        for (Robot robot : robots) {
            try {
                robot.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (Robot robot : robots) {
            System.out.println(
                    robot.getClass().getSimpleName() + " realizó " + robot.getTrabajosRealizados() + " trabajos.");
        }
    }
}
