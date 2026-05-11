package asistencia.asistencias.dto;

import java.time.LocalDate;
import asistencia.asistencias.models.EstadoAsistencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaDTO {
    private Long id;
    private Long estudianteId;
    private Long cursoId;
    private Long profesorId; 
    private LocalDate fecha;
    private EstadoAsistencia estado;
    private String observaciones;
}