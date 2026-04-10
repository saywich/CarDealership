package ng.saywich.presentation.controllers.parts;

import ng.saywich.application.contracts.carparts.IPartService;
import ng.saywich.application.contracts.carparts.operations.AddPart;
import ng.saywich.application.contracts.carparts.operations.DeletePart;
import ng.saywich.application.contracts.carparts.operations.QueryParts;
import ng.saywich.application.contracts.carparts.operations.UpdatePart;
import ng.saywich.domain.car.parts.Interior;
import ng.saywich.application.contracts.dtos.parts.InteriorDto;
import ng.saywich.application.contracts.mappers.parts.InteriorDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<InteriorDto>> getInteriors() {
        List<InteriorDto> interiorDtoList = service.query(new QueryParts.Request<>(new UUID[0]))
            .parts().stream()
            .map(interiorDtoMapper::toInteriorDto)
            .toList();

        if (interiorDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(interiorDtoList);
    }

    @PostMapping("/")
    public ResponseEntity<InteriorDto> createInterior(@RequestBody InteriorDto dto) {
        return ResponseEntity.ok(interiorDtoMapper.toInteriorDto(
            service.add(new AddPart.Request<>(interiorDtoMapper.toInterior(dto)))
                .part()));
    }

    @PutMapping("/")
    public ResponseEntity<InteriorDto> updateInterior(@RequestBody InteriorDto dto) {
        return ResponseEntity.ok(interiorDtoMapper.toInteriorDto(
            service.update(new UpdatePart.Request<>(interiorDtoMapper.toInterior(dto)))
                .part()));
    }

    @DeleteMapping("/")
    public ResponseEntity<InteriorDto> deleteInterior(@RequestBody InteriorDto dto) {
        return ResponseEntity.ok(interiorDtoMapper.toInteriorDto(
            service.delete(new DeletePart.Request<>(interiorDtoMapper.toInterior(dto)))
                .part()));
    }
}