package asistencia.asistencias.models;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "justificacion")
@AllArgsConstructor
@NoArgsConstructor
public class Justificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long asistenciaId;
    private String motivo;
    private LocalDate fechaPresentacion;
    private String documentoUrl;
    private String estado; // Pendiente, Aprobada, Rechazada
}
