package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.dtos.HeladeraDTO;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.NoSuchElementException;

public class ObtenerHeladeraController {

    private Fachada fachadaHeladera; // No le puedo poner FachadaHeladeras porque no tiene el buscarXId.
    public ObtenerHeladeraController(Fachada fachadaHeladera) {
        super();
        this.fachadaHeladera = fachadaHeladera;
    }


    public void obtenerHeladera(Context context) throws NotFoundResponse {
        /*
        try {
            Heladera heladera = this.heladeraRepository.findById(Integer.parseInt(context.pathParam("id")));
            context.json(heladeraMapper.map(heladera));
            // context.status(200).result("Heladera obtenida correctamente."); Cuando termine de testear, ponerlo así (descomentar esta línea y sacar la anterior).
        } catch (NoSuchElementException e) {
            throw new NotFoundResponse("Heladera no encontrada.");
        }
        */
        try {
            HeladeraDTO heladeraDTO = this.fachadaHeladera.buscarXId((Integer.parseInt(context.pathParam("id"))));
            context.json(heladeraDTO);
            // context.status(200).result("Heladera obtenida correctamente."); Cuando termine de testear, ponerlo así (descomentar esta línea y sacar la anterior).
        } catch (NoSuchElementException e) {
            throw new NotFoundResponse("Heladera no encontrada.");
        }


    }
}
