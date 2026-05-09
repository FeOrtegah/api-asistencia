package asistencia.asistencias.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import asistencia.asistencias.dto.AsistenciaDTO;
import asistencia.asistencias.dto.AsistenciaBulkDTO;
import asistencia.asistencias.models.Asistencia;
import asistencia.asistencias.repository.AsistenciaRepository;

@Service
@Transactional
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    public List<AsistenciaDTO> getAllAsistencias() {
        return asistenciaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AsistenciaDTO getAsistenciaById(Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id: " + id));
        return mapToDTO(asistencia);
    }

    public List<AsistenciaDTO> getAsistenciasPorCursoYFecha(Long cursoId, LocalDate fecha) {
        return asistenciaRepository.findByCursoIdAndFecha(cursoId, fecha).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AsistenciaDTO> getHistorialEstudiante(Long estudianteId) {
        return asistenciaRepository.findByEstudianteId(estudianteId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AsistenciaDTO saveAsistencia(AsistenciaDTO dto) {
        Asistencia asistencia = mapToEntity(dto);
        Asistencia saved = Objects.requireNonNull(asistenciaRepository.save(asistencia), "Error al guardar");
        return mapToDTO(saved);
    }

    public List<AsistenciaDTO> guardarCargaMasiva(AsistenciaBulkDTO bulkDTO) {
        List<Asistencia> entidades = bulkDTO.getAsistencias().stream()
                .map(dto -> {
                    Asistencia ent = mapToEntity(dto);
                    ent.setCursoId(bulkDTO.getCursoId());
                    ent.setProfesorId(bulkDTO.getProfesorId());
                    if (ent.getFecha() == null) ent.setFecha(LocalDate.now());
                    return ent;
                })
                .collect(Collectors.toList());

        return asistenciaRepository.saveAll(entidades).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AsistenciaDTO updateAsistencia(Long id, AsistenciaDTO dto) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id: " + id));

        asistencia.setFecha(dto.getFecha());
        asistencia.setEstado(dto.getEstado());
        asistencia.setCursoId(dto.getCursoId());
        asistencia.setProfesorId(dto.getProfesorId());
        asistencia.setEstudianteId(dto.getEstudianteId());
        asistencia.setObservaciones(dto.getObservaciones());

        return mapToDTO(asistenciaRepository.save(asistencia));
    }

    public void deleteAsistencia(Long id) {
        asistenciaRepository.deleteById(id);
    }

    private AsistenciaDTO mapToDTO(Asistencia entity) {
        return new AsistenciaDTO(
            entity.getId(),
            entity.getEstudianteId(),
            entity.getCursoId(),
            entity.getProfesorId(),
            entity.getFecha(),
            entity.getEstado(),
            entity.getObservaciones()
        );
    }

    private Asistencia mapToEntity(AsistenciaDTO dto) {
        Asistencia entity = new Asistencia();
        entity.setId(dto.getId());
        entity.setEstudianteId(dto.getEstudianteId());
        entity.setCursoId(dto.getCursoId());
        entity.setProfesorId(dto.getProfesorId());
        entity.setFecha(dto.getFecha());
        entity.setEstado(dto.getEstado());
        entity.setObservaciones(dto.getObservaciones());
        return entity;
    }
}