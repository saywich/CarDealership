package ng.saywich.presentation.controllers.parts;

import ng.saywich.application.contracts.carparts.IPartService;
import ng.saywich.application.contracts.carparts.operations.AddPart;
import ng.saywich.application.contracts.carparts.operations.DeletePart;
import ng.saywich.application.contracts.carparts.operations.QueryParts;
import ng.saywich.application.contracts.carparts.operations.UpdatePart;
import ng.saywich.domain.car.parts.Rims;
import ng.saywich.application.contracts.dtos.parts.RimsDto;
import ng.saywich.application.contracts.mappers.parts.RimsDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("parts/rims")
public class RimsController {

    @Autowired
    private RimsDtoMapper rimsDtoMapper;

    @Autowired
    private IPartService<Rims> service;

    @GetMapping("/")
    public ResponseEntity<List<RimsDto>> getRims() {
        List<RimsDto> rimsDtoList = service.query(new QueryParts.Request<>(new UUID[0]))
            .parts().stream()
            .map(rimsDtoMapper::toRimsDto)
            .toList();

        if (rimsDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(rimsDtoList);
    }

    @PostMapping("/")
    public ResponseEntity<RimsDto> createRims(@RequestBody RimsDto dto) {
        return ResponseEntity.ok(rimsDtoMapper.toRimsDto(
            service.add(new AddPart.Request<>(rimsDtoMapper.toRims(dto)))
                .part()));
    }

    @PutMapping("/")
    public ResponseEntity<RimsDto> updateRims(@RequestBody RimsDto dto) {
        return ResponseEntity.ok(rimsDtoMapper.toRimsDto(
            service.update(new UpdatePart.Request<>(rimsDtoMapper.toRims(dto)))
                .part()));
    }

    @DeleteMapping("/")
    public ResponseEntity<RimsDto> deleteRims(@RequestBody RimsDto dto) {
        return ResponseEntity.ok(rimsDtoMapper.toRimsDto(
            service.delete(new DeletePart.Request<>(rimsDtoMapper.toRims(dto)))
                .part()));
    }
}