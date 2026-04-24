package ng.saywich.storageservice.infrastructure.persistence.abstractions.parts.options;

import ng.saywich.storageservice.application.abstractions.carparts.IPartOptionRepository;
import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Color;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.options.IColorOptionDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options.ColorOptionMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.ColorOptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ColorOptionRepository implements IPartOptionRepository<Color> {
    @Autowired
    private IColorOptionDaoRepository repository;

    @Autowired
    private ColorOptionMapper mapper;

    @Override
    public void create(CarOption<Color> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public List<CarOption<Color>> query(UUID[] partIds) {
        return repository.findAllById(List.of(partIds)).stream()
            .map(o -> mapper.toCarOption(o))
            .toList();
    }

    @Override
    public List<CarOption<Color>> queryForModel(UUID modelId) {
        return repository.findByModelId(modelId).stream()
            .map(o -> mapper.toCarOption(o))
            .toList();
    }

    @Override
    public void update(CarOption<Color> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public void delete(CarOption<Color> option) {
        Optional<ColorOptionDao> dao = repository.findById(option.optionId());

        dao.ifPresent((o) -> {
            ColorOptionDao removedDao = ColorOptionDao.builder()
                .modelId(o.getModelId())
                .color(o.getColor())
                .priceDelta(o.getPriceDelta())
                .updatedAt(o.getUpdatedAt())
                .createdAt(o.getCreatedAt())
                .removed(true)
                .build();

            repository.save(removedDao);
        });
    }
}
