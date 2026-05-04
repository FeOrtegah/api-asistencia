package asistencia.asistencias.models;
import java.time.LocalDate;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "anotacion")

public class Anotacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private Long estudianteId;
    private Long profesorId;

    private LocalDate fecha;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private TipoAnotacion tipo;
}