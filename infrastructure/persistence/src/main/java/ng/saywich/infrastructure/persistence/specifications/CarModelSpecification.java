package ng.saywich.infrastructure.persistence.specifications;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import ng.saywich.application.contracts.car.operations.models.CarModelFilter;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.infrastructure.persistence.models.cars.CarBrandDao;
import ng.saywich.infrastructure.persistence.models.cars.CarModelDao;
import ng.saywich.infrastructure.persistence.models.cars.OptionSetDao;
import ng.saywich.infrastructure.persistence.models.parts.ColorDao;
import ng.saywich.infrastructure.persistence.models.parts.EngineDao;
import ng.saywich.infrastructure.persistence.models.parts.TransmissionDao;
import ng.saywich.infrastructure.persistence.models.parts.options.BodyTypeOptionDao;
import ng.saywich.infrastructure.persistence.models.parts.options.ColorOptionDao;
import ng.saywich.infrastructure.persistence.models.parts.options.EngineOptionDao;
import ng.saywich.infrastructure.persistence.models.parts.options.TransmissionOptionDao;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CarModelSpecification implements Specification<CarModelDao> {
    private CarModelFilter filter;

    @Override
    public @Nullable Predicate toPredicate(
        Root<CarModelDao> root,
        CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder
    ) {
        List<Predicate> predicates = new ArrayList<>();

        Join<CarModelDao, OptionSetDao> optionJoin = root.join("defaultOptionSet", JoinType.INNER);

        if (filter.priceRange() != null) {
            if (filter.priceRange().start() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    root.get("price"),
                    filter.priceRange().start().value()
                ));
            }

            if (filter.priceRange().end() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                    root.get("price"),
                    filter.priceRange().end().value()
                ));
            }
        }

        Join<OptionSetDao, EngineOptionDao> engineOptionJoin
            = optionJoin.join("engine", JoinType.INNER);
        Join<EngineOptionDao, EngineDao> engineJoin
            = engineOptionJoin.join("engine", JoinType.INNER);

        if (filter.powerRange() != null) {
            if (filter.powerRange().start() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    engineJoin.get("power"), filter.powerRange().start().value()));
            }

            if (filter.powerRange().end() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                    engineJoin.get("power"), filter.powerRange().end().value()));
            }
        }

        if (filter.volumeRange() != null) {
            if (filter.volumeRange().start() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    engineJoin.get("volume"), filter.volumeRange().start().value()));
            }

            if (filter.volumeRange().start() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                    engineJoin.get("volume"), filter.volumeRange().end().value()));
            }
        }

        if (filter.fuelType() != null) {
            predicates.add(
                criteriaBuilder.equal(
                    engineJoin.get("fuelType"),
                    criteriaBuilder.literal(filter.fuelType().name())
                )
            );
        }

        Join<OptionSetDao, BodyTypeOptionDao> bodyTypeOptionJoin
            = optionJoin.join("bodyType", JoinType.INNER);

        if (filter.bodyType() != null) {
            predicates.add(
                criteriaBuilder.equal(
                    bodyTypeOptionJoin.get("bodyType"),
                    criteriaBuilder.literal(filter.bodyType().name())
                )
            );
        }

        Join<OptionSetDao, TransmissionOptionDao> transmissionOptionJoin
            = optionJoin.join("transmission", JoinType.INNER);
        Join<TransmissionOptionDao, TransmissionDao> transmissionJoin
            = transmissionOptionJoin.join("transmission", JoinType.INNER);

        if (filter.transmissionType() != null) {
            predicates.add(
                criteriaBuilder.equal(
                    transmissionJoin.get("transmissionType"),
                    criteriaBuilder.literal(filter.transmissionType().name())
                )
            );
        }

        if (filter.driveTrain() != null) {
            predicates.add(
                criteriaBuilder.equal(
                    transmissionJoin.get("driveTrain"),
                    criteriaBuilder.literal(filter.driveTrain().name())
                )
            );
        }

        Join<OptionSetDao, ColorOptionDao> colorOptionJoin
            = optionJoin.join("color", JoinType.INNER);
        Join<ColorOptionDao, ColorDao> colorJoin
            = colorOptionJoin.join("color", JoinType.INNER);

        if (filter.colorName() != null && !filter.colorName().isBlank()) {
            predicates.add(
                criteriaBuilder.equal(
                    criteriaBuilder.lower(colorJoin.get("colorName")),
                    filter.colorName().toLowerCase()
                )
            );
        }

        Join<CarModelDao, CarBrandDao> carBrandDaoJoin
            = root.join("brand", JoinType.INNER);

        if (filter.carFullName() != null) {
            if (filter.carFullName().brandName() != null) {
                predicates.add(criteriaBuilder.equal(
                    carBrandDaoJoin.get("name"),
                    filter.carFullName().brandName()
                ));
            }

            if (filter.carFullName().modelName().isPresent()) {
                predicates.add(criteriaBuilder.equal(
                    root.get("name"),
                    filter.carFullName().modelName().get()
                ));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
