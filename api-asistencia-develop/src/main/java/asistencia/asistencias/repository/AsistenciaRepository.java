package asistencia.asistencias.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import asistencia.asistencias.models.Asistencia;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    List<Asistencia> findByCursoIdAndFecha(Long cursoId, LocalDate fecha);

    List<Asistencia> findByEstudianteId(Long estudianteId);
}
