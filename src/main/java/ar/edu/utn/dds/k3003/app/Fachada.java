package ar.edu.utn.dds.k3003.app;

import ar.edu.utn.dds.k3003.facades.FachadaHeladeras;
import ar.edu.utn.dds.k3003.facades.FachadaViandas;
import ar.edu.utn.dds.k3003.facades.dtos.*;
import ar.edu.utn.dds.k3003.model.Heladera;
import ar.edu.utn.dds.k3003.model.Temperatura;
import ar.edu.utn.dds.k3003.repositories.HeladeraMapper;
import ar.edu.utn.dds.k3003.repositories.HeladeraRepository;
import ar.edu.utn.dds.k3003.repositories.TemperaturaMapper;

import java.util.*;

public class Fachada implements FachadaHeladeras {

    private final HeladeraRepository heladeraRepository;
    private HeladeraMapper heladeraMapper;
    private TemperaturaMapper temperaturaMapper;
    private FachadaViandas fachadaViandas;

    public Fachada() {
        this.heladeraRepository = new HeladeraRepository();
        this.heladeraMapper = new HeladeraMapper();
        this.temperaturaMapper = new TemperaturaMapper();
    }

    public Fachada(HeladeraRepository heladeraRepository, HeladeraMapper heladeraMapper, TemperaturaMapper temperaturaMapper) {
        this.heladeraRepository = heladeraRepository;
        this.heladeraMapper = heladeraMapper;
        this.temperaturaMapper = temperaturaMapper;
    }

    @Override
    public HeladeraDTO agregar(HeladeraDTO heladeraDTO) {
        Heladera heladera = new Heladera(heladeraDTO.getId(), heladeraDTO.getNombre()); // 2do param?: heladeraDTO.getNombre());
        heladera = this.heladeraRepository.save(heladera);
        return this.heladeraMapper.map(heladera);
    }

    @Override
    public void depositar(Integer heladeraId, String qrVianda) throws NoSuchElementException {
        Heladera heladera = this.heladeraRepository.findById(heladeraId);
        heladera.guardar(qrVianda);
        ViandaDTO viandaDTO = this.fachadaViandas.buscarXQR(qrVianda);
        this.fachadaViandas.modificarEstado(viandaDTO.getCodigoQR(), EstadoViandaEnum.DEPOSITADA);
    }

    @Override
    public Integer cantidadViandas(Integer heladeraId) throws NoSuchElementException {
        return this.heladeraRepository.findById(heladeraId).getCantidadViandas();
    }

    @Override
    public void retirar(RetiroDTO retiroDTO) throws NoSuchElementException {
        Heladera heladera = this.heladeraRepository.findById(retiroDTO.getHeladeraId());
        heladera.retirar(retiroDTO.getQrVianda());
        ViandaDTO viandaDTO = this.fachadaViandas.buscarXQR(retiroDTO.getQrVianda());
        this.fachadaViandas.modificarEstado(viandaDTO.getCodigoQR(), EstadoViandaEnum.RETIRADA);
    }

    @Override
    public void temperatura(TemperaturaDTO temperaturaDTO) {
        Temperatura temperatura = new Temperatura(temperaturaDTO.getTemperatura(), temperaturaDTO.getHeladeraId(), temperaturaDTO.getFechaMedicion());
        Heladera heladera = this.heladeraRepository.findById(temperaturaDTO.getHeladeraId());
        this.heladeraRepository.save(heladera);
        heladera.setTemperatura(temperatura);
    }

    @Override
    public List<TemperaturaDTO> obtenerTemperaturas(Integer heladeraId) {
        Heladera heladera = this.heladeraRepository.findById(heladeraId);
        List<TemperaturaDTO> temperaturasHeladera = new ArrayList<>();
        heladera.getTemperaturas().forEach(temperatura -> temperaturasHeladera.add(this.temperaturaMapper.map(temperatura)));
        return temperaturasHeladera;
    }
    @Override
    public void setViandasProxy(FachadaViandas fachadaViandas) {
        this.fachadaViandas = fachadaViandas;

    }

    public HeladeraDTO buscarXId(Integer heladeraId) throws NoSuchElementException {
        return this.heladeraMapper.map(this.heladeraRepository.findById(heladeraId));
    }
}
