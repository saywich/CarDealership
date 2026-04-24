package ng.saywich.storageservice.infrastructure.persistence.abstractions.parts.options;

import ng.saywich.storageservice.application.abstractions.carparts.IPartOptionRepository;
import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.enums.BodyType;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.options.IBodyTypeOptionDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options.BodyTypeOptionMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.BodyTypeOptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BodyTypeOptionRepository implements IPartOptionRepository<BodyType> {

    @Autowired
    private IBodyTypeOptionDaoRepository repository;

    @Autowired
    private BodyTypeOptionMapper mapper;

    @Override
    public void create(CarOption<BodyType> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public List<CarOption<BodyType>> query(UUID[] partIds) {
        return repository.findAllById(List.of(partIds)).stream()
            .map(mapper::toCarOption)
            .toList();
    }

    @Override
    public List<CarOption<BodyType>> queryForModel(UUID modelId) {
        return repository.findByModelId(modelId).stream()
            .map(mapper::toCarOption)
            .toList();
    }

    @Override
    public void update(CarOption<BodyType> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public void delete(CarOption<BodyType> option) {
        Optional<BodyTypeOptionDao> dao = repository.findById(option.optionId());

        dao.ifPresent(o -> {
            BodyTypeOptionDao removedDao = BodyTypeOptionDao.builder()
                .modelId(o.getModelId())
                .bodyType(o.getBodyType())
                .priceDelta(o.getPriceDelta())
                .updatedAt(o.getUpdatedAt())
                .createdAt(o.getCreatedAt())
                .removed(true)
                .build();

            repository.save(removedDao);
        });
    }
}