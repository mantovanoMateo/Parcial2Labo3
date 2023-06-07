package Models;

public class Registro {
    private String DNI;
    private Integer temperatura;

    public Registro(String DNI, Integer temperatura) {
        this.DNI = DNI;
        this.temperatura = temperatura;
    }

    public Registro() {
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

    @Override
    public String toString() {
        return "Registro{" +
                "DNI='" + DNI + '\'' +
                ", temperatura=" + temperatura +
                '}';
    }
}
