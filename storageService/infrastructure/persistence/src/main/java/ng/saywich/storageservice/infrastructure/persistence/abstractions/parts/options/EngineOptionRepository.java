package ng.saywich.storageservice.infrastructure.persistence.abstractions.parts.options;

import ng.saywich.storageservice.application.abstractions.carparts.IPartOptionRepository;
import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Engine;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.options.IEngineOptionDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options.EngineOptionMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.EngineOptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EngineOptionRepository implements IPartOptionRepository<Engine> {

    @Autowired
    private IEngineOptionDaoRepository repository;

    @Autowired
    private EngineOptionMapper mapper;

    @Override
    public void create(CarOption<Engine> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public List<CarOption<Engine>> query(UUID[] partIds) {
        return repository.findAllById(List.of(partIds)).stream()
            .map(mapper::toCarOption)
            .toList();
    }

    @Override
    public List<CarOption<Engine>> queryForModel(UUID modelId) {
        return repository.findByModelId(modelId).stream()
            .map(mapper::toCarOption)
            .toList();
    }

    @Override
    public void update(CarOption<Engine> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public void delete(CarOption<Engine> option) {
        Optional<EngineOptionDao> dao = repository.findById(option.optionId());

        dao.ifPresent(o -> {
            EngineOptionDao removedDao = EngineOptionDao.builder()
                .modelId(o.getModelId())
                .engine(o.getEngine())
                .priceDelta(o.getPriceDelta())
                .updatedAt(o.getUpdatedAt())
                .createdAt(o.getCreatedAt())
                .removed(true)
                .build();

            repository.save(removedDao);
        });
    }
}