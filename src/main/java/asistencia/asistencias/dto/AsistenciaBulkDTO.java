package asistencia.asistencias.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaBulkDTO {
    private Long cursoId;
    private Long profesorId;
    private List<AsistenciaDTO> asistencias; 
}