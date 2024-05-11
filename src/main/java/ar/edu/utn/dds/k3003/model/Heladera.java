package ar.edu.utn.dds.k3003.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Heladera {

    @Setter
    @Getter
    private Integer id;

    private Collection<String> viandas;

    @Getter
    private String nombre;

    @Getter
    private LinkedList<Temperatura> temperaturas;

    public Heladera() {
        super();
    }

    public Heladera(Integer id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.viandas = new ArrayList<>();
        this.temperaturas = new LinkedList<>();

    }


    public void guardar(String qrVianda){
        this.viandas.add(qrVianda);
    }

    public void retirar(String qrVianda) {
        this.viandas.remove(qrVianda);
    }

    public Integer getCantidadViandas() {
        return this.viandas.size();
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperaturas.addFirst(temperatura);
    }

    public LocalDateTime getFechaMedicion() {
        return this.temperaturas.getFirst().getFechaMedicion();
    }

    public Temperatura getUltimaTemperatura() {
        return this.temperaturas.get(0);
    }

}
