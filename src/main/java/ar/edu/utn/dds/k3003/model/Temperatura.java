package ar.edu.utn.dds.k3003.model;

import lombok.Getter;
import java.time.LocalDateTime;

public class Temperatura {
    @Getter
    private Integer temperatura;
    @Getter
    private Integer heladeraId;
    @Getter
    private LocalDateTime fechaMedicion;

    public Temperatura(Integer temperatura, Integer heladeraId, LocalDateTime fechaMedicion) {
        this.temperatura = temperatura;
        this.heladeraId = heladeraId;
        this.fechaMedicion = fechaMedicion;
    }

}
