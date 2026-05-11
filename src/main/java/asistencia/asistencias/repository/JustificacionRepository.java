package asistencia.asistencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import asistencia.asistencias.models.Justificacion;

public interface JustificacionRepository extends JpaRepository<Justificacion, Long> {

    List<Justificacion> findByAsistenciaId(Long asistenciaId);

    List<Justificacion> findByEstado(String estado);
    
}
