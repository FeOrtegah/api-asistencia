package asistencia.asistencias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import asistencia.asistencias.models.Justificacion;
import asistencia.asistencias.repository.JustificacionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class JustificacionService {

    private final JustificacionRepository justificacionRepository;

    public JustificacionService(JustificacionRepository justificacionRepository) {
        this.justificacionRepository = justificacionRepository;
    }

    public List<Justificacion> getAllJustificaciones() {
        return justificacionRepository.findAll();
    }

    public Optional<Justificacion> getJustificacionById(Long id) {
        return justificacionRepository.findById(id);
    }
    
    public Justificacion saveJustificacion(Justificacion justificacion) {
        return justificacionRepository.save(justificacion);
    }

    public void deleteJustificacion(Long id) {
        justificacionRepository.deleteById(id);
    }

    public Justificacion updateJustificacion(Long id, Justificacion justificacionDetails) {
        Justificacion justificacion = justificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Justificación no encontrada con id: " + id));

        justificacion.setMotivo(justificacionDetails.getMotivo());
        justificacion.setFechaPresentacion(justificacionDetails.getFechaPresentacion());
        justificacion.setDocumentoUrl(justificacionDetails.getDocumentoUrl());
        justificacion.setEstado(justificacionDetails.getEstado());
        justificacion.setAsistenciaId(justificacionDetails.getAsistenciaId());

        return justificacionRepository.save(justificacion);
    }

    public List<Justificacion> getJustificacionesPorAsistencia(Long asistenciaId) {
        return justificacionRepository.findByAsistenciaId(asistenciaId);
    }

    public List<Justificacion> getJustificacionesPorEstado(String estado) {
        return justificacionRepository.findByEstado(estado);
    }
    
}
