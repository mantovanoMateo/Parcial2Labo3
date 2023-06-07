package Models;

import java.util.UUID;

public class CasosSospechosos {
    private UUID nroDeKit;
    private Registro registro;

    public CasosSospechosos() {
    }

    public CasosSospechosos(UUID nroDeKit, Registro registro) {
        this.nroDeKit = nroDeKit;
        this.registro = registro;
    }

    public UUID getNroDeKit() {
        return nroDeKit;
    }

    public void setNroDeKit(UUID nroDeKit) {
        this.nroDeKit = nroDeKit;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    @Override
    public String toString() {
        return "casosSospechosos{" +
                "temperatura=" + nroDeKit +
                ", registro=" + registro +
                '}';
    }
}
