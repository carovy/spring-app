package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.dtos.MonopatinKmDTO;
import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.entities.Pausa;
import org.example.monopatinmicroservice.entities.Viaje;
import org.example.monopatinmicroservice.feignClients.ManteinanceFeignClient;
import org.example.monopatinmicroservice.models.Mantenimiento;
import org.example.monopatinmicroservice.repositories.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepository monopatinRepository;
    @Autowired
    private ViajeService viajeService;
    @Autowired
    private PausaService pausaService;
    @Autowired
    private ManteinanceFeignClient mfc;

    public List<Monopatin> getAll(){
        return monopatinRepository.findAll();
    }

    public Monopatin add(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
    }

    public Monopatin getById(Long id) {
        return monopatinRepository.findById(id).orElse(null);
    }

    public List<Monopatin> getMonopatinesPorViajesPorAnio(Integer anio, Integer xViajes) {
        return monopatinRepository.getMonopatinesPorViajesPorAnio(anio, xViajes);
    }

    public Monopatin delete(Long id) {
        Monopatin monopatin = monopatinRepository.findById(id).orElse(null);

        if (monopatin != null) {
            monopatinRepository.deleteById(id);
        }

        return monopatin;
    }

    public ArrayList<Monopatin> getClosestMonopatins(int posx, int posy) throws Exception {
        try{
            List<Monopatin> monopatines= this.monopatinRepository.getMonopatinesEnRadio1km(posx,posy);
            List<Monopatin> respuesta = new ArrayList<>();
            for (Monopatin monopatin : monopatines) {
                respuesta.add(monopatin);
            }
            return (ArrayList<Monopatin>) respuesta;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ArrayList<Monopatin> getMonopatinsByStatus(String status) {
        ResponseEntity<?> monopatins = this.mfc.getAllManteinanceByStatus(status);
        ArrayList<LinkedHashMap<?, ?>> response = (ArrayList<LinkedHashMap<?, ?>>) monopatins.getBody();
        ArrayList<Monopatin> result = new ArrayList<>();

        for(LinkedHashMap<?, ?> l: response){
            Integer aux = (Integer) l.get("id_monopatin");
            Long id_monopatin = aux.longValue();

            Monopatin m = this.monopatinRepository.findById(id_monopatin).orElse(null);
            if(m != null){
                result.add(m);
            }
        }

        return result;
    }

    //FUNCIONA, ARREGLADO
    public ResponseEntity<?> enviarMonopatinAMantenimiento(Integer monopatin) {
        Long id_monopatin = monopatin.longValue();
        int limiteKm = 40000; //limite de km a partir del cual llevar a mantener
        Integer tiempoUso = 4380; //limite, implica 6 meses en horas
        Map<String, String> response = new HashMap<>();
        Monopatin m = this.monopatinRepository.findById(id_monopatin).orElse(null);
        if(m == null){
            response.put("error", "monopatin no encontrado");
            return ResponseEntity.badRequest().body(response);
        }
        if(m.getKmRecorridos()<limiteKm && m.getTiempoUso() < tiempoUso){
            response.put("Inhabilitado", "el monopatín no necesita mantenimiento");
            return ResponseEntity.ok().body(response);
        } else{
            ResponseEntity<?> alreadyExists = this.mfc.getManteinanceByMonopatinId(id_monopatin);
            if(alreadyExists.getBody() == null){
                ResponseEntity<?> result = this.mfc.saveManteinance(id_monopatin);
                LinkedHashMap<?, ?> l = (LinkedHashMap<?, ?>) result.getBody();
                Integer auxId = (Integer) l.get("id");
                Long id = auxId.longValue();
                Integer auxIdMonopatin = (Integer) l.get("id_monopatin");
                Long idMonopatin = auxIdMonopatin.longValue();

                Mantenimiento mant = new Mantenimiento(id, idMonopatin, l.get("estado").toString());
                return ResponseEntity.ok().body(mant);
            } else {
                this.mfc.updateStatus(monopatin, "no disponible");
                response.put("success", "monopatin enviado a mantenimiento");
                return ResponseEntity.ok().body(response);
            }
        }
    }

    public ResponseEntity<?> cambiarEstadoMonopatin(Integer id_monopatin, String estado) {
        int idInt = id_monopatin;
        Map<String, String> response = new HashMap<>();
        if(estado == null || (!estado.equals("activo") && !estado.equals("no disponible"))){
            response.put("error", "estado invalido o nulo");
            return ResponseEntity.badRequest().body(response);
        } else if(this.mfc.getManteinanceByMonopatinId(id_monopatin.longValue()) == null){
            response.put("error", "monopatin no encontrado");
            return ResponseEntity.badRequest().body(response);
        } else{
            this.mfc.updateStatus(idInt, estado);
            response.put("success", "estado del monopatin cambiado");
            return ResponseEntity.ok().body(response);
        }
    }

    public ArrayList<MonopatinKmDTO> getMonopatinesPorKM(boolean pausa) {
        ArrayList<Monopatin> m = this.monopatinRepository.getMonopatinesPorKM();
        if(pausa){
            for(Monopatin m1 : m){
                ArrayList<Viaje> viajes = this.viajeService.getViajesByIdMonopatin(m1.getId());
                int tiempo = 0;
                int duracionViaje = 0;
                for(Viaje v : viajes){
                    duracionViaje += v.getDuracion();
                    tiempo += (this.pausaService.getPausasByViajeId(v.getId()) + duracionViaje);
                }
                m1.setTiempoUsoConPausas(tiempo);
                m1.setTiempoUso(duracionViaje);
            }
        }

        ArrayList<MonopatinKmDTO> response = new ArrayList<>();
        for(Monopatin m1 : m){
            response.add(new MonopatinKmDTO(m1.getId(), m1.getKmRecorridos(), m1.getTiempoUso(), m1.getTiempoUsoConPausas()));
        }
        return response;
    }
}
