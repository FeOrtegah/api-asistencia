package asistencia.asistencias.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado_asistencia")


public enum EstadoAsistencia {
    PRESENTE,
    AUSENTE,
    ATRASADO,
    JUSTIFICADO
}