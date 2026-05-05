package asistencia.asistencias.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "asistencia_bulk")
@AllArgsConstructor
@NoArgsConstructor

public class AsistenciaBulkDTO {
    private Long cursoId;
    private Long profesorId;
    private List<Asistencia> asistencias;
}
