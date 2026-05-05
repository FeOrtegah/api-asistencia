package asistencia.asistencias.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import asistencia.asistencias.repository.AnotacionRepository;
import asistencia.asistencias.models.Anotacion;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@SuppressWarnings("null")
public class AnotacionService {
    private final AnotacionRepository anotacionRepository;

    public AnotacionService(AnotacionRepository anotacionRepository) {
        this.anotacionRepository = anotacionRepository;
    }

    public List<Anotacion> getAllAnotaciones() {
        return anotacionRepository.findAll();
    }

    public Optional<Anotacion> getAnotacionById(Long id) {
        return anotacionRepository.findById(id);
    }

    public Anotacion saveAnotacion(Anotacion anotacion) {
        return anotacionRepository.save(anotacion);
    }

    public void deleteAnotacion(Long id) {
        anotacionRepository.deleteById(id);
    }

    public Optional<Anotacion> updateAnotacion(Long id, Anotacion anotacionDetails) {
        return anotacionRepository.findById(id)
                .map(anotacion -> {
                    anotacion.setDescripcion(anotacionDetails.getDescripcion());
                    anotacion.setFecha(anotacionDetails.getFecha());
                    anotacion.setTipo(anotacionDetails.getTipo());
                    anotacion.setProfesorId(anotacionDetails.getProfesorId());
                    anotacion.setEstudianteId(anotacionDetails.getEstudianteId());
                    return anotacionRepository.save(anotacion);
                });
    }

    public List<Anotacion> getAnotacionesEstudiante(Long estudianteId) {
        return anotacionRepository.findByEstudianteId(estudianteId);
    }

    public List<Anotacion> getAnotacionesProfesores(Long profesorId) {
        return anotacionRepository.findByProfesorId(profesorId);
    }

}
