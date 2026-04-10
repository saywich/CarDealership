package ng.saywich.presentation.controllers.parts;

import ng.saywich.application.contracts.carparts.IPartService;
import ng.saywich.application.contracts.carparts.operations.AddPart;
import ng.saywich.application.contracts.carparts.operations.DeletePart;
import ng.saywich.application.contracts.carparts.operations.QueryParts;
import ng.saywich.application.contracts.carparts.operations.UpdatePart;
import ng.saywich.domain.car.parts.Transmission;
import ng.saywich.application.contracts.dtos.parts.TransmissionDto;
import ng.saywich.application.contracts.mappers.parts.TransmissionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TransmissionDto>> getTransmissions() {
        List<TransmissionDto> transmissionDtoList = service.query(new QueryParts.Request<>(new UUID[0]))
            .parts().stream()
            .map(transmissionDtoMapper::toTransmissionDto)
            .toList();

        if (transmissionDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(transmissionDtoList);
    }

    @PostMapping("/")
    public ResponseEntity<TransmissionDto> createTransmission(@RequestBody TransmissionDto dto) {
        return ResponseEntity.ok(transmissionDtoMapper.toTransmissionDto(
            service.add(new AddPart.Request<>(transmissionDtoMapper.toTransmission(dto)))
                .part()));
    }

    @PutMapping("/")
    public ResponseEntity<TransmissionDto> updateTransmission(@RequestBody TransmissionDto dto) {
        return ResponseEntity.ok(transmissionDtoMapper.toTransmissionDto(
            service.update(new UpdatePart.Request<>(transmissionDtoMapper.toTransmission(dto)))
                .part()));
    }

    @DeleteMapping("/")
    public ResponseEntity<TransmissionDto> deleteTransmission(@RequestBody TransmissionDto dto) {
        return ResponseEntity.ok(transmissionDtoMapper.toTransmissionDto(
            service.delete(new DeletePart.Request<>(transmissionDtoMapper.toTransmission(dto)))
                .part()));
    }
}