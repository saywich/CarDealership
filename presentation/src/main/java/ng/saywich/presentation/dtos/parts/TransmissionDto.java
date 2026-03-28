package ng.saywich.presentation.dtos.parts;

import ng.saywich.domain.car.enums.DriveTrain;
import ng.saywich.domain.car.enums.TransmissionType;

import java.util.UUID;

public record TransmissionDto(
    UUID partId,
    String name,
    int gearsCount,
    TransmissionType type,
    DriveTrain driveTrain
) { }
