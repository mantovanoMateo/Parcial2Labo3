package Exceptions;

import java.util.UUID;

public class Covid19PositvoException extends Exception{

    private String barrio;
    private UUID nroDeKit;

    public Covid19PositvoException(String barrio, UUID nroDeKit) {
        this.barrio = barrio;
        this.nroDeKit = nroDeKit;
    }

    public Covid19PositvoException() {
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public UUID getNroDeKit() {
        return nroDeKit;
    }

    public void setNroDeKit(UUID nroDeKit) {
        this.nroDeKit = nroDeKit;
    }

    @Override
    public String toString() {
        return "covid19PositvoException{" +
                "barrio='" + barrio + '\'' +
                ", nroDeKit=" + nroDeKit +
                "} " + "\n";
    }
}
