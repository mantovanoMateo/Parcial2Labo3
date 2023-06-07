package Models;

import Exceptions.Covid19PositvoException;
import Exceptions.NoMasReactivosException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SistemaDeSaludMunicipal {

    //Elegi trabajar con fila ya que al indicar que se trabaja por orden de llegada me parecio lo mas adaptable a la situacion
    private Queue<Persona> filaPacientes;
    private Integer reactivos;

    public SistemaDeSaludMunicipal(Queue<Persona> filaPacientes, Integer reactivos) {
        this.filaPacientes = filaPacientes;
        this.reactivos = reactivos;
    }

    public SistemaDeSaludMunicipal() {
        this.filaPacientes=new LinkedList<>();
        this.reactivos=3;
    }

    public Queue<Persona> getFilaPacientes() {
        return filaPacientes;
    }

    public void setFilaPacientes(Queue<Persona> filaPacientes) {
        this.filaPacientes = filaPacientes;
    }

    public Integer getReactivos() {
        return reactivos;
    }

    public void reabastecerReactivos(Integer aumento){
        this.reactivos+=aumento;
    }


    public void setReactivos(Integer reactivos) {
        this.reactivos = reactivos;
    }

    public Boolean agregarPersona(Persona persona){
        Boolean agregado=false;
        if(!filaPacientes.isEmpty()){
            if(!filaPacientes.contains(persona)){
                filaPacientes.add(persona);
            }
        }else {
            filaPacientes.add(persona);
            agregado=true;
        }

        return agregado;
    }

    public HashMap<UUID,Registro> testear(){
        Random rand= new Random();
        HashMap<UUID,Registro> tablaDeTesteados=new HashMap<>();
        Persona personaTesteada;
        Persona aux=filaPacientes.poll();
        Boolean esPrimero=true;

        /*
        primero habia hecho esto, despues me di cuenta que poniendo un booleano me ahorraba de duplicar este codigo  :)
        try {
            if (this.reactivos==0){
                throw new noMasReactivosException();
            }else {
                Registro registro=new Registro(aux.getDNI(),aux.getTemperatura());
                tablaDeTesteados.put(aux.getNroKit(),registro);
                this.reactivos--;
            }

        }catch (noMasReactivosException e){
            this.reabastecerReactivos(10);
            System.out.println("reabastecimios reactivos");
        }finally {
            filaPacientes.add(aux);
        }
         */

        while(!this.filaPacientes.peek().equals(aux)){
            if(esPrimero==true){
                personaTesteada=aux;
                esPrimero=false;
            }else {
                personaTesteada=this.filaPacientes.poll();
            }
            try {
                if (this.reactivos==0){
                    throw new NoMasReactivosException();
                }else {
                    Registro registro=new Registro(personaTesteada.getDNI(),personaTesteada.getTemperatura());
                    tablaDeTesteados.put(personaTesteada.getNroKit(),registro);
                    this.reactivos--;
                }

            }catch (NoMasReactivosException e){
                this.reabastecerReactivos(10);
                System.out.println("reabastecimios reactivos");
                Registro registro=new Registro(personaTesteada.getDNI(),personaTesteada.getTemperatura());
                tablaDeTesteados.put(personaTesteada.getNroKit(),registro);
                this.reactivos--;
            }finally {
                filaPacientes.add(personaTesteada);
            }

        }
       return tablaDeTesteados;
    }

    public Persona buscarPorNroDeKit(UUID nroDeKit){
        Persona buscado=null;
        Persona X=null;
        Persona principioFila=filaPacientes.peek();
        Boolean encontrado=false;
        int i=0;

        while (encontrado==false){
            X=filaPacientes.poll();
            if(X.getNroKit().equals(nroDeKit)){
                encontrado=true;
                buscado=X;
            }else {
                i++;
            }
            filaPacientes.add(X);
        }

        while(!filaPacientes.peek().equals(principioFila)){
            filaPacientes.add(filaPacientes.poll());
        }
        return buscado;
    }

    public ArrayList<Covid19PositvoException> aislar(HashMap<UUID,Registro> tablaDeTesteados, File file){
        ArrayList<Covid19PositvoException> covid19Positivo=new ArrayList<>();
        for (Map.Entry entry: tablaDeTesteados.entrySet()){
            Registro testeado= (Registro) entry.getValue();
            try {
                if (testeado.getTemperatura()>=38){
                    throw new Covid19PositvoException(buscarPorNroDeKit((UUID) entry.getKey()).getBarrio(), (UUID) entry.getKey());
                }
            }catch (Covid19PositvoException e){
                    covid19Positivo.add(e);
                    ///aca deberia agregar al archivo.dat pero no me acuerdo como se hacia :/
            }
        }
        return covid19Positivo;
    }

    public void persistirResultados(File file){
        HashMap<String, ArregloGenerico> datos=new HashMap<>();
        Queue<Persona> aux= this.filaPacientes;
        Persona persona=null;
        ObjectMapper mapper=new ObjectMapper();

        datos.put("sanos",new ArregloGenerico<Persona>());
        datos.put("aislar",new ArregloGenerico<CasosSospechosos>());
        while (!aux.isEmpty()){
            persona=aux.poll();
            if (persona.getTemperatura()<38){
                datos.get("sanos").agregar(persona);
            }else{
                datos.get("aislar").agregar(new CasosSospechosos(persona.getNroKit(),new Registro(persona.getDNI(), persona.getTemperatura())));
            }
        }
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file,datos);
        }catch (IOException e){
            System.out.println("Error en escritura de archivo");
        }
    }




}
