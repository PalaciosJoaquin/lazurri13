import java.util.ArrayList;
import javax.swing.JOptionPane;

class MesaTrabajo {
    private int mesasDisponibles;

    public MesaTrabajo(int mesas) {
        this.mesasDisponibles = mesas;
    }

    public synchronized void usarMesa() throws InterruptedException {
        while (mesasDisponibles <= 0) {
            wait();
        }
        mesasDisponibles--;
    }

    public synchronized void liberarMesa() {
        mesasDisponibles++;
        notify();
    }
}

class RobotImpresor extends Thread {
    private MesaTrabajo mesa;
    private int trabajos;

    public RobotImpresor(MesaTrabajo mesa, int trabajos) {
        this.mesa = mesa;
        this.trabajos = trabajos;
    }

    public void run() {
        for (int i = 0; i < trabajos; i++) {
            try {
                mesa.usarMesa();
                System.out.println("Robot Impresor " + getName() + " está imprimiendo...");
                Thread.sleep(1000);
                mesa.liberarMesa();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class RobotPintor extends Thread {
    private MesaTrabajo mesa;
    private int trabajos;

    public RobotPintor(MesaTrabajo mesa, int trabajos) {
        this.mesa = mesa;
        this.trabajos = trabajos;
    }

    public void run() {
        for (int i = 0; i < trabajos; i++) {
            try {
                mesa.usarMesa();
                System.out.println("Robot Pintor " + getName() + " está pintando...");
                Thread.sleep(1000);
                mesa.liberarMesa();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SimuladorRobots {

    public static void main(String[] args) {
        String mesasInput = JOptionPane.showInputDialog("Ingrese la cantidad de mesas de trabajo:");
        String trabajosInput = JOptionPane.showInputDialog("Ingrese la cantidad de trabajos por robot:");

        int mesas = Integer.parseInt(mesasInput);
        int trabajos = Integer.parseInt(trabajosInput);

        MesaTrabajo mesaTrabajo = new MesaTrabajo(mesas);

        ArrayList<RobotImpresor> impresores = new ArrayList<>();
        ArrayList<RobotPintor> pintores = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            RobotImpresor impresor = new RobotImpresor(mesaTrabajo, trabajos);
            impresor.start();
            impresores.add(impresor);
        }

        for (int i = 0; i < 3; i++) {
            RobotPintor pintor = new RobotPintor(mesaTrabajo, trabajos);
            pintor.start();
            pintores.add(pintor);
        }

        for (RobotImpresor impresor : impresores) {
            try {
                impresor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (RobotPintor pintor : pintores) {
            try {
                pintor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JOptionPane.showMessageDialog(null, "Simulación finalizada.");
    }
}
