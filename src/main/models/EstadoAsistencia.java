package main.models;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado_asistencia")


public enum EstadoAsistencia {
    PRESENTE,
    AUSENTE,
    ATRASADO,
    JUSTIFICADO
}