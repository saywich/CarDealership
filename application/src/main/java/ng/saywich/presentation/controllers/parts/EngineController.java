package ng.saywich.presentation.controllers.parts;

import ng.saywich.application.contracts.carparts.IPartService;
import ng.saywich.application.contracts.carparts.operations.AddPart;
import ng.saywich.application.contracts.carparts.operations.DeletePart;
import ng.saywich.application.contracts.carparts.operations.QueryParts;
import ng.saywich.application.contracts.carparts.operations.UpdatePart;
import ng.saywich.domain.car.parts.Engine;
import ng.saywich.application.contracts.dtos.parts.EngineDto;
import ng.saywich.application.contracts.mappers.parts.EngineDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("parts/engines")
public class EngineController {
    @Autowired
    private EngineDtoMapper engineDtoMapper;

    @Autowired
    private IPartService<Engine> service;

    @GetMapping("/")
    public ResponseEntity<List<EngineDto>> getEngines() {
        List<EngineDto> engineDtoList = service.query(new QueryParts.Request<>(new UUID[0]))
            .parts().stream()
            .map(engineDtoMapper::toEngineDto)
            .toList();

        if (engineDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(engineDtoList);
    }

    @PostMapping("/")
    public ResponseEntity<EngineDto> createEngine(@RequestBody EngineDto engineDto) {
        return ResponseEntity.ok(engineDtoMapper.toEngineDto(
            service.add(new AddPart.Request<>(engineDtoMapper.toEngine(engineDto)))
            .part()));
    }

    @PutMapping("/")
    public ResponseEntity<EngineDto> updateEngine(@RequestBody EngineDto engineDto) {
        return ResponseEntity.ok(engineDtoMapper.toEngineDto(
            service.update(new UpdatePart.Request<>(engineDtoMapper.toEngine(engineDto)))
                .part()));
    }

    @DeleteMapping("/")
    public ResponseEntity<EngineDto> deleteEngine(@RequestBody EngineDto engineDto) {
        return ResponseEntity.ok(engineDtoMapper.toEngineDto(
            service.delete(new DeletePart.Request<>(engineDtoMapper.toEngine(engineDto)))
                .part()));
    }
}
