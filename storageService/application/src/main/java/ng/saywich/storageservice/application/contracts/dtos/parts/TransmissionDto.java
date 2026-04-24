package ng.saywich.storageservice.application.contracts.dtos.parts;

import ng.saywich.shared.domain.car.enums.DriveTrain;
import ng.saywich.shared.domain.car.enums.TransmissionType;

import java.util.UUID;

public record TransmissionDto(
    UUID partId,
    String name,
    int gearsCount,
    TransmissionType type,
    DriveTrain driveTrain
) { }
