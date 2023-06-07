import Exceptions.Covid19PositvoException;
import Models.Persona;
import Models.Registro;
import Models.SistemaDeSaludMunicipal;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //creamos las personas
        Persona persona=new Persona("mateo","mantovano",23,"sancarlos","42322678","mantenido");
        Persona persona1=new Persona("david","cabrera",27,"chauvin","40458234","UX/UI");
        Persona persona2=new Persona("lautaro","celuce",24,"sancarlos","41234789","ing. naval");
        Persona persona3=new Persona("julian","gimenez",27,"puerto","40329765","java developer");
        Persona persona4=new Persona("bautista","franco",26,"lostroncos","40555777","vendedor");
        Persona persona5=new Persona("lautaro","echeverria",27,"primerajunta","40333888","backend developer");

        //creamos el sistema de salud
        SistemaDeSaludMunicipal saludMunicipal=new SistemaDeSaludMunicipal();

        //agregamos las personas
        saludMunicipal.agregarPersona(persona);
        saludMunicipal.agregarPersona(persona1);
        saludMunicipal.agregarPersona(persona2);
        saludMunicipal.agregarPersona(persona3);
        saludMunicipal.agregarPersona(persona4);
        saludMunicipal.agregarPersona(persona5);

        //testeamos las personas
        HashMap<UUID, Registro> tablaDeTesteos=saludMunicipal.testear();
        System.out.println("Mostramos los testeados\n-------------------------------------");
        for (Map.Entry<UUID,Registro> entry: tablaDeTesteos.entrySet()){
            System.out.println(entry.getKey()+" \\ "+entry.getValue());
        }
        System.out.println("-------------------------------------\nFin de la tabla de testeos\n-------------------------------");


        //aislar las personas
        File archiResultados=new File("archi.dat");
        System.out.println("aislamos y mostramos lo aislado");
        ArrayList<Covid19PositvoException> resultados=saludMunicipal.aislar(tablaDeTesteos,archiResultados);
        System.out.println(resultados);

        //grabarEnJason
        File archiDatosPersistidos=new File("datosPersistidos.json");
        System.out.println("Ver datos del JSON en el archivo JSON se veran mas lindos y legibles que si meto todo en Un string y lo muestro por en este SOUT");

    }
}