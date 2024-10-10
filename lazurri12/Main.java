import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Robot> robots = new ArrayList<>();
        int trabajosPorRobot = 5;

        for (int i = 0; i < 3; i++) {
            robots.add(new RobotImpresor(trabajosPorRobot));
        }

        for (int i = 0; i < 3; i++) {
            robots.add(new RobotPintor(trabajosPorRobot));
        }

        Taller taller = new Taller(robots);
        taller.iniciarSimulacion();
    }
}
