package ng.saywich.presentation.controllers.parts;

import ng.saywich.application.contracts.carparts.IPartService;
import ng.saywich.application.contracts.carparts.operations.AddPart;
import ng.saywich.application.contracts.carparts.operations.DeletePart;
import ng.saywich.application.contracts.carparts.operations.QueryParts;
import ng.saywich.application.contracts.carparts.operations.UpdatePart;
import ng.saywich.domain.car.parts.Color;
import ng.saywich.presentation.dtos.parts.ColorDto;
import ng.saywich.presentation.mappers.parts.ColorDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("parts/colors")
public class ColorController {
    @Autowired
    private ColorDtoMapper colorDtoMapper;

    @Autowired
    private IPartService<Color> service;

    @GetMapping("/")
    public ResponseEntity<List<ColorDto>> getColors() {
        List<ColorDto> colorDtoList = service.query(new QueryParts.Request<>(new UUID[0]))
            .parts().stream()
            .map(colorDtoMapper::toColorDto)
            .toList();

        if (colorDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(colorDtoList);
    }

    @PostMapping("/")
    public ResponseEntity<ColorDto> createColor(@RequestBody ColorDto dto) {
        return ResponseEntity.ok(colorDtoMapper.toColorDto(
            service.add(new AddPart.Request<>(colorDtoMapper.toColor(dto)))
                .part()));
    }

    @PutMapping("/")
    public ResponseEntity<ColorDto> updateColor(@RequestBody ColorDto dto) {
        return ResponseEntity.ok(colorDtoMapper.toColorDto(
            service.update(new UpdatePart.Request<>(colorDtoMapper.toColor(dto)))
                .part()));
    }

    @DeleteMapping("/")
    public ResponseEntity<ColorDto> deleteColor(@RequestBody ColorDto dto) {
        return ResponseEntity.ok(colorDtoMapper.toColorDto(
            service.delete(new DeletePart.Request<>(colorDtoMapper.toColor(dto)))
                .part()));
    }
}