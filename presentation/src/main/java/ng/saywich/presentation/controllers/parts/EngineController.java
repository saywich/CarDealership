package ng.saywich.presentation.controllers.parts;

import ng.saywich.application.contracts.carparts.IPartService;
import ng.saywich.application.contracts.carparts.operations.AddPart;
import ng.saywich.application.contracts.carparts.operations.DeletePart;
import ng.saywich.application.contracts.carparts.operations.QueryParts;
import ng.saywich.application.contracts.carparts.operations.UpdatePart;
import ng.saywich.domain.car.parts.Engine;
import ng.saywich.presentation.dtos.parts.EngineDto;
import ng.saywich.presentation.mappers.parts.EngineDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<EngineDto> getEngines() {
        return service.query(new QueryParts.Request<>(new UUID[0]))
            .parts().stream()
            .map(engineDtoMapper::toEngineDto)
            .toList();
    }

    @PostMapping("/")
    public EngineDto createEngine(@RequestBody EngineDto engineDto) {
        return engineDtoMapper.toEngineDto(
            service.add(new AddPart.Request<>(engineDtoMapper.toEngine(engineDto)))
            .part());
    }

    @PutMapping("/")
    public EngineDto updateEngine(@RequestBody EngineDto engineDto) {
        return engineDtoMapper.toEngineDto(
            service.update(new UpdatePart.Request<>(engineDtoMapper.toEngine(engineDto)))
                .part());
    }

    @DeleteMapping("/")
    public EngineDto deleteEngine(@RequestBody EngineDto engineDto) {
        return engineDtoMapper.toEngineDto(
            service.delete(new DeletePart.Request<>(engineDtoMapper.toEngine(engineDto)))
                .part());
    }
}
