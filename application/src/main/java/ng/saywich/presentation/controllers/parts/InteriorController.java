package ng.saywich.presentation.controllers.parts;

import ng.saywich.application.contracts.carparts.IPartService;
import ng.saywich.application.contracts.carparts.operations.AddPart;
import ng.saywich.application.contracts.carparts.operations.DeletePart;
import ng.saywich.application.contracts.carparts.operations.QueryParts;
import ng.saywich.application.contracts.carparts.operations.UpdatePart;
import ng.saywich.domain.car.parts.Interior;
import ng.saywich.presentation.dtos.parts.InteriorDto;
import ng.saywich.presentation.mappers.parts.InteriorDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("parts/interiors")
public class InteriorController {

    @Autowired
    private InteriorDtoMapper interiorDtoMapper;

    @Autowired
    private IPartService<Interior> service;

    @GetMapping("/")
    public List<InteriorDto> getInteriors() {
        return service.query(new QueryParts.Request<>(new UUID[0]))
            .parts().stream()
            .map(interiorDtoMapper::toInteriorDto)
            .toList();
    }

    @PostMapping("/")
    public InteriorDto createInterior(@RequestBody InteriorDto dto) {
        return interiorDtoMapper.toInteriorDto(
            service.add(new AddPart.Request<>(interiorDtoMapper.toInterior(dto)))
                .part());
    }

    @PutMapping("/")
    public InteriorDto updateInterior(@RequestBody InteriorDto dto) {
        return interiorDtoMapper.toInteriorDto(
            service.update(new UpdatePart.Request<>(interiorDtoMapper.toInterior(dto)))
                .part());
    }

    @DeleteMapping("/")
    public InteriorDto deleteInterior(@RequestBody InteriorDto dto) {
        return interiorDtoMapper.toInteriorDto(
            service.delete(new DeletePart.Request<>(interiorDtoMapper.toInterior(dto)))
                .part());
    }
}