package asistencia.asistencias.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import asistencia.asistencias.models.Asistencia;
import asistencia.asistencias.service.AsistenciaService;

@RestController
@RequestMapping("/api/v1/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<List<Asistencia>> getAllAsistencias() {
        List<Asistencia> asistencias = asistenciaService.getAllAsistencias();
        if (asistencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Long id) {
        return asistenciaService.getAsistenciaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/curso")
    public ResponseEntity<List<Asistencia>> getAsistenciasPorCursoYFecha(
            @RequestParam Long cursoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<Asistencia> asistencias = asistenciaService.getAsistenciasPorCursoYFecha(cursoId, fecha);
        if (asistencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Asistencia>> getHistorialEstudiante(@PathVariable Long estudianteId) {
        List<Asistencia> asistencias = asistenciaService.getHistorialEstudiante(estudianteId);
        if (asistencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asistencias);
    }

    @PostMapping
    public ResponseEntity<Asistencia> createAsistencia(@RequestBody Asistencia asistencia) {
        Asistencia created = asistenciaService.saveAsistencia(asistencia);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> updateAsistencia(@PathVariable Long id, @RequestBody Asistencia asistenciaDetails) {
        Asistencia updated = asistenciaService.updateAsistencia(id, asistenciaDetails);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Long id) {
        asistenciaService.deleteAsistencia(id);
        return ResponseEntity.noContent().build();
    }
}
