package ng.saywich.presentation.controllers.parts;

import ng.saywich.application.contracts.carparts.IPartService;
import ng.saywich.application.contracts.carparts.operations.AddPart;
import ng.saywich.application.contracts.carparts.operations.DeletePart;
import ng.saywich.application.contracts.carparts.operations.QueryParts;
import ng.saywich.application.contracts.carparts.operations.UpdatePart;
import ng.saywich.domain.car.parts.Transmission;
import ng.saywich.presentation.dtos.parts.TransmissionDto;
import ng.saywich.presentation.mappers.parts.TransmissionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("parts/transmissions")
public class TransmissionController {

    @Autowired
    private TransmissionDtoMapper transmissionDtoMapper;

    @Autowired
    private IPartService<Transmission> service;

    @GetMapping("/")
    public List<TransmissionDto> getTransmissions() {
        return service.query(new QueryParts.Request<>(new UUID[0]))
            .parts().stream()
            .map(transmissionDtoMapper::toTransmissionDto)
            .toList();
    }

    @PostMapping("/")
    public TransmissionDto createTransmission(@RequestBody TransmissionDto dto) {
        return transmissionDtoMapper.toTransmissionDto(
            service.add(new AddPart.Request<>(transmissionDtoMapper.toTransmission(dto)))
                .part());
    }

    @PutMapping("/")
    public TransmissionDto updateTransmission(@RequestBody TransmissionDto dto) {
        return transmissionDtoMapper.toTransmissionDto(
            service.update(new UpdatePart.Request<>(transmissionDtoMapper.toTransmission(dto)))
                .part());
    }

    @DeleteMapping("/")
    public TransmissionDto deleteTransmission(@RequestBody TransmissionDto dto) {
        return transmissionDtoMapper.toTransmissionDto(
            service.delete(new DeletePart.Request<>(transmissionDtoMapper.toTransmission(dto)))
                .part());
    }
}