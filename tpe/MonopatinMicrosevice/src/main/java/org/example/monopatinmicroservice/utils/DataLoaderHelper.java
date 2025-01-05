package org.example.monopatinmicroservice.utils;

import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.entities.Parada;
import org.example.monopatinmicroservice.entities.Pausa;
import org.example.monopatinmicroservice.entities.Viaje;
import org.example.monopatinmicroservice.repositories.MonopatinRepository;
import org.example.monopatinmicroservice.repositories.ParadaRepository;
import org.example.monopatinmicroservice.repositories.PausaRepository;
import org.example.monopatinmicroservice.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static jakarta.xml.bind.DatatypeConverter.parseInteger;

@Component
public class DataLoaderHelper {

    private final MonopatinRepository monopatinRepository;

    private final ParadaRepository paradaRepository;

    private final PausaRepository pausaRepository;

    private final ViajeRepository viajeRepository;

    public DataLoaderHelper(MonopatinRepository monopatinRepository, ParadaRepository paradaRepository, PausaRepository pausaRepository, ViajeRepository viajeRepository) {
        this.monopatinRepository = monopatinRepository;
        this.paradaRepository = paradaRepository;
        this.pausaRepository = pausaRepository;
        this.viajeRepository = viajeRepository;
    }

    @Transactional
    public void loadParadas() {
        List<String[]> paradas = CSVReaderHelper.readCSV("MonopatinMicrosevice/src/main/java/org/example/monopatinmicroservice/utils/paradas.csv");

        for (String[] parada : paradas.subList(1, paradas.size())) {
            Parada p = new Parada();
//            p.setId(Long.parseLong(parada[0]));
            // Set Monopatin
//            Monopatin m = monopatinRepository.findById(Long.parseLong(parada[1])).orElseThrow();
//            p.setMonopatin(m);
            paradaRepository.save(p);
        }
    }

    public void loadMonopatines(){
        List<String[]> monopatines = CSVReaderHelper.readCSV("MonopatinMicrosevice/src/main/java/org/example/monopatinmicroservice/utils/monopatines.csv");
        for (String[] mono : monopatines.subList(1, monopatines.size())) {
            Monopatin m = new Monopatin();
            //m.setId((long) Long.parseLong(mono[0]));
            m.setKmRecorridos(Float.parseFloat(mono[1]));
            m.setTiempoUso(parseInteger(mono[2], 0));
            m.setTiempoUsoConPausas(parseInteger(mono[2], 0));
            // Set Parada
            Parada p = paradaRepository.findById(Long.parseLong(mono[4])).orElseThrow();
            m.setParada(p);
            m.setPosX(Integer.parseInt(mono[5]));
            m.setPosY(Integer.parseInt(mono[6]));
            monopatinRepository.save(m);
        }
    }

    public void loadViajes() throws ParseException {
        List<String[]> viajes = CSVReaderHelper.readCSV("MonopatinMicrosevice/src/main/java/org/example/monopatinmicroservice/utils/viajes.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (String[] viaje : viajes.subList(1, viajes.size())) {
            Viaje v = new Viaje();
            //v.setId(Long.parseLong(viaje[0]));
            LocalDate fecha = LocalDate.parse(viaje[1], formatter);
            v.setFecha(fecha);
            v.setDuracion(Integer.parseInt(viaje[2]));
            v.setId_usuario(Long.parseLong(viaje[3]));
            // Set Monopatin
            Monopatin m = monopatinRepository.findById(Long.parseLong(viaje[4])).orElseThrow();
            v.setMonopatin(m);
            v.setKilometros(Float.parseFloat(viaje[5]));
            viajeRepository.save(v);
        }
    }

    public void loadPausas(){
        List<String[]> pausas = CSVReaderHelper.readCSV("MonopatinMicrosevice/src/main/java/org/example/monopatinmicroservice/utils/pausas.csv");
        for (String[] pausa : pausas.subList(1, pausas.size())) {
            Pausa p = new Pausa();
            //p.setId(Long.parseLong(pausa[0]));
            p.setTiempo(Integer.parseInt(pausa[1]));
            // Set Viaje
            Viaje v = viajeRepository.findById(Long.parseLong(pausa[2])).orElseThrow();
            p.setViaje(v);
            pausaRepository.save(p);
        }
    }

    private Integer parseInteger(String value, int defaultValue) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue; // Retorna un valor predeterminado si hay un error
        }
    }
}
