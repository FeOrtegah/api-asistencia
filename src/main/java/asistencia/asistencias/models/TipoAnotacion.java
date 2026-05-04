package asistencia.asistencias.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_anotacion")

public enum TipoAnotacion {
    POSITIVA,
    NEGATIVA
}