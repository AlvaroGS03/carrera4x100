package carrera;

public class Atleta extends Thread {
    private String nombre;
    private Carrera carrera;

    public Atleta (String nombre, Carrera carrera) {
        this.nombre = nombre;
        this.carrera = carrera;
    }  

    public void run(){
        carrera.correr(this);
    }

    public String getNombre () {
        return nombre;
    }
}
