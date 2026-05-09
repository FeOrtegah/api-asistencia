package asistencia.asistencias.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import asistencia.asistencias.dto.AsistenciaDTO;
import asistencia.asistencias.dto.AsistenciaBulkDTO;
import asistencia.asistencias.service.AsistenciaService;

@RestController
@RequestMapping("/api/v1/asistencias")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }


    @GetMapping
    public ResponseEntity<List<AsistenciaDTO>> getAllAsistencias() {
        List<AsistenciaDTO> asistencias = asistenciaService.getAllAsistencias();
        return asistencias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(asistencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> getAsistenciaById(@PathVariable Long id) {
        return ResponseEntity.ok(asistenciaService.getAsistenciaById(id));
    }

    @GetMapping("/curso")
    public ResponseEntity<List<AsistenciaDTO>> getAsistenciasPorCursoYFecha(
            @RequestParam Long cursoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<AsistenciaDTO> asistencias = asistenciaService.getAsistenciasPorCursoYFecha(cursoId, fecha);
        return asistencias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(asistencias);
    }

    @PostMapping
    public ResponseEntity<AsistenciaDTO> createAsistencia(@Valid @RequestBody AsistenciaDTO dto) {
        AsistenciaDTO created = asistenciaService.saveAsistencia(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    // CARGA MASIVA (BULK): Aquí es donde el profesor manda la lista completa
    @PostMapping("/bulk")
    public ResponseEntity<List<AsistenciaDTO>> createBulkAsistencia(@Valid @RequestBody AsistenciaBulkDTO bulkDto) {
        List<AsistenciaDTO> created = asistenciaService.guardarCargaMasiva(bulkDto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> updateAsistencia(@PathVariable Long id, @Valid @RequestBody AsistenciaDTO dto) {
        return ResponseEntity.ok(asistenciaService.updateAsistencia(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Long id) {
        asistenciaService.deleteAsistencia(id);
        return ResponseEntity.noContent().build();
    }
}