package asistencia.asistencias.service;
import org.springframework.stereotype.Service;
import asistencia.asistencias.repository.AsistenciaRepository;
import jakarta.transaction.Transactional;
import asistencia.asistencias.models.Asistencia;
import java.util.List;
import java.util.Optional;

@Service 
@Transactional
@SuppressWarnings("null")
public class AsistenciaService {
    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }

    public Optional<Asistencia> getAsistenciaById(Long id) {
        return asistenciaRepository.findById(id);
    }

    public Asistencia saveAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    public List<Asistencia> saveAllAsistencias(List<Asistencia> asistencias) {
        return asistenciaRepository.saveAll(asistencias);
    }

    public void deleteAsistencia(Long id) {
        asistenciaRepository.deleteById(id);
    }

    public Asistencia updateAsistencia(Long id, Asistencia asistenciaDetails) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id: " + id));

        asistencia.setFecha(asistenciaDetails.getFecha());
        asistencia.setEstado(asistenciaDetails.getEstado());
        asistencia.setCursoId(asistenciaDetails.getCursoId());
        asistencia.setProfesorId(asistenciaDetails.getProfesorId());
        asistencia.setEstudianteId(asistenciaDetails.getEstudianteId());

        return asistenciaRepository.save(asistencia);
    }

    public List<Asistencia> getAsistenciasPorCursoYFecha(Long cursoId, java.time.LocalDate fecha) {
        return asistenciaRepository.findByCursoIdAndFecha(cursoId, fecha);
    }

    public List<Asistencia> getHistorialEstudiante(Long estudianteId) {
        return asistenciaRepository.findByEstudianteId(estudianteId);
    }
}
