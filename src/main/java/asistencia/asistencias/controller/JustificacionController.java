package asistencia.asistencias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import asistencia.asistencias.models.Justificacion;
import asistencia.asistencias.service.JustificacionService;

@RestController
@RequestMapping("/api/v1/justificaciones")
public class JustificacionController {

    @Autowired
    private JustificacionService justificacionService;

    @GetMapping
    public ResponseEntity<List<Justificacion>> getAllJustificaciones() {
        List<Justificacion> justificaciones = justificacionService.getAllJustificaciones();
        if (justificaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(justificaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Justificacion> getJustificacionById(@PathVariable Long id) {
        return justificacionService.getJustificacionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/asistencia/{asistenciaId}")
    public ResponseEntity<List<Justificacion>> getJustificacionesPorAsistencia(@PathVariable Long asistenciaId) {
        List<Justificacion> justificaciones = justificacionService.getJustificacionesPorAsistencia(asistenciaId);
        if (justificaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(justificaciones);
    }

    @GetMapping(params = "estado")
    public ResponseEntity<List<Justificacion>> getJustificacionesPorEstado(@RequestParam String estado) {
        List<Justificacion> justificaciones = justificacionService.getJustificacionesPorEstado(estado);
        if (justificaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(justificaciones);
    }

    @PostMapping
    public ResponseEntity<Justificacion> createJustificacion(@RequestBody Justificacion justificacion) {
        Justificacion created = justificacionService.saveJustificacion(justificacion);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Justificacion> updateJustificacion(@PathVariable Long id, @RequestBody Justificacion justificacionDetails) {
        Justificacion updated = justificacionService.updateJustificacion(id, justificacionDetails);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJustificacion(@PathVariable Long id) {
        justificacionService.deleteJustificacion(id);
        return ResponseEntity.noContent().build();
    }
}
