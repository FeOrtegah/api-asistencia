package asistencia.asistencias.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import asistencia.asistencias.repository.AnotacionRepository;
import asistencia.asistencias.model.Anotacion;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@SuppressWarnings("null")
public class AnotacionService {
    private final AnotacionRepository anotacionRepository;

    public AnotacionService(AnotacionService anotacionService) {
        this.anotacionRepository = (AnotacionRepository) anotacionService;
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

    public Anotacion updateAnotacion(Long id, Anotacion anotacionDetails) {
        Anotacion anotacion = anotacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anotación no encontrada con id: " + id));

        anotacion.setDescripcion(anotacionDetails.getDescripcion());
        anotacion.setFecha(anotacionDetails.getFecha());

        return anotacionRepository.save(anotacion);
    }
}
