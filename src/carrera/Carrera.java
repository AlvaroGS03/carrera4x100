package carrera;

public class Carrera {

    private static final Object testigo = new Object();
    private static final int NUM_ATLETAS = 4;
    private long tiempoInicio;

    public void iniciarCarrera(Atleta[] atletas) {
        tiempoInicio = System.currentTimeMillis();
        for (int i = 0; i < NUM_ATLETAS; i++) {
            atletas[i].start();
        }
        synchronized (testigo) {
            testigo.notify();
        }
    }

    public void correr(Atleta atleta) {
        synchronized (testigo) {
            try {
                testigo.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long tiempoInicioAtleta = System.currentTimeMillis();
        System.out.println("El atleta " + atleta.getNombre() + " ha comenzado a correr");

        try {
            Thread.sleep((long) (Math.random() * 2000.0 + 9000.0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long tiempoFinAtleta = System.currentTimeMillis();
        System.out.println("El atleta " + atleta.getNombre() + " ha terminado en " + (tiempoFinAtleta - tiempoInicioAtleta) / 1000.0 + " segundos");

        synchronized (testigo) {
            testigo.notify();
        }

        if (atleta.getNombre().equals("Atleta " + NUM_ATLETAS)) {
            System.out.println("La carrera ha terminado en "
                    + (tiempoFinAtleta - tiempoInicio) / 1000.0 + " segundos.");
        }
    }

    public static void main(String[] args) {
        Carrera carrera = new Carrera();
        Atleta[] atletas = new Atleta[NUM_ATLETAS];
        for (int i = 0; i < NUM_ATLETAS; i++) {
            atletas[i] = new Atleta("Atleta " + (i + 1), carrera);
        }
        carrera.iniciarCarrera(atletas);
    }
}
