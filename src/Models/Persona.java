package Models;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class Persona {
    private String nombre;
    private String apellido;
    private Integer edad;
    private String barrio;
    private String DNI;
    private String ocupacion;
    private UUID nroKit;
    private Integer temperatura;
    private Random random=new Random();

    public Persona(String nombre, String apellido, Integer edad, String barrio, String DNI, String ocupacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.barrio = barrio;
        this.DNI = DNI;
        this.ocupacion = ocupacion;
        this.nroKit=UUID.randomUUID();
        this.temperatura=random.nextInt(36,39);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        edad = edad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        barrio = barrio;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public UUID getNroKit() {
        return nroKit;
    }

    public void setNroKit(UUID nroKit) {
        this.nroKit = nroKit;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", Edad=" + edad +
                ", Barrio='" + barrio + '\'' +
                ", DNI='" + DNI + '\'' +
                ", ocupacion='" + ocupacion + '\'' +
                ", Nro de Kit='"+ nroKit+'\''+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(DNI, persona.DNI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DNI);
    }
}
