public abstract class Robot extends Thread {
    protected int trabajosRealizados = 0;
    protected int trabajosPorHacer;

    public Robot(int trabajosPorHacer) {
        this.trabajosPorHacer = trabajosPorHacer;
    }

    public int getTrabajosRealizados() {
        return trabajosRealizados;
    }

    @Override
    public void run() {
        while (trabajosRealizados < trabajosPorHacer) {
            realizarTrabajo();
            trabajosRealizados++;
        }
    }

    protected abstract void realizarTrabajo();
}
