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
import org.springframework.web.bind.annotation.RestController;

import asistencia.asistencias.models.Anotacion;
import asistencia.asistencias.service.AnotacionService;

@RestController 
@RequestMapping("api/v1/anotaciones")
public class AnotacioController {
    @Autowired
    private AnotacionService anotacionService;
    @GetMapping
    public ResponseEntity<List<Anotacion>> getAllAnotaciones() {
        List<Anotacion> anotaciones = anotacionService.getAllAnotaciones();
        if (anotaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(anotaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anotacion> getAnotacionById(@PathVariable Long id) {
        Anotacion anotacion = anotacionService.getAnotacionById(id).orElse(null);
        if (anotacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(anotacion);
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Anotacion>> getAnotacionesPorEstudiante(@PathVariable Long estudianteId) {
        List<Anotacion> anotaciones = anotacionService.getAnotacionesEstudiante(estudianteId);
        if (anotaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(anotaciones);
    }

    @GetMapping("/profesor/{profesorId}")
    public ResponseEntity<List<Anotacion>> getAnotacionesPorProfesor(@PathVariable Long profesorId) {
        List<Anotacion> anotaciones = anotacionService.getAnotacionesProfesores(profesorId);
        if (anotaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(anotaciones);
    }

    @PostMapping
    public ResponseEntity<Anotacion> createAnotacion(@RequestBody Anotacion anotacion) {
        Anotacion createdAnotacion = anotacionService.saveAnotacion(anotacion);
        return ResponseEntity.ok(createdAnotacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Anotacion> updateAnotacion(@PathVariable Long id, @RequestBody Anotacion anotacionDetails) {
        return anotacionService.updateAnotacion(id, anotacionDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnotacion(@PathVariable Long id) {
        anotacionService.deleteAnotacion(id);
        return ResponseEntity.noContent().build();
    }
    
}
