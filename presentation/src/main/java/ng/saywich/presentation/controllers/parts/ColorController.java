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
    public List<ColorDto> getColors() {
        return service.query(new QueryParts.Request<>(new UUID[0]))
            .parts().stream()
            .map(colorDtoMapper::toColorDto)
            .toList();
    }

    @PostMapping("/")
    public ColorDto createColor(@RequestBody ColorDto dto) {
        return colorDtoMapper.toColorDto(
            service.add(new AddPart.Request<>(colorDtoMapper.toColor(dto)))
                .part());
    }

    @PutMapping("/")
    public ColorDto updateColor(@RequestBody ColorDto dto) {
        return colorDtoMapper.toColorDto(
            service.update(new UpdatePart.Request<>(colorDtoMapper.toColor(dto)))
                .part());
    }

    @DeleteMapping("/")
    public ColorDto deleteColor(@RequestBody ColorDto dto) {
        return colorDtoMapper.toColorDto(
            service.delete(new DeletePart.Request<>(colorDtoMapper.toColor(dto)))
                .part());
    }
}