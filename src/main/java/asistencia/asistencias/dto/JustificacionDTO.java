package asistencia.asistencias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JustificacionDTO {
    private Long id;
    private Long asistenciaId;
    private String motivo;
    private LocalDate fechaPresentacion;
    private String documentoUrl;
    private String estado;
}