package ng.saywich.storageservice.infrastructure.persistence.abstractions.parts.options;

import ng.saywich.storageservice.application.abstractions.carparts.IPartOptionRepository;
import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Interior;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.options.IInteriorOptionDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options.InteriorOptionMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.InteriorOptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InteriorOptionRepository implements IPartOptionRepository<Interior> {

    @Autowired
    private IInteriorOptionDaoRepository repository;

    @Autowired
    private InteriorOptionMapper mapper;

    @Override
    public void create(CarOption<Interior> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public List<CarOption<Interior>> query(UUID[] partIds) {
        return repository.findAllById(List.of(partIds)).stream()
            .map(mapper::toCarOption)
            .toList();
    }

    @Override
    public List<CarOption<Interior>> queryForModel(UUID modelId) {
        return repository.findByModelId(modelId).stream()
            .map(mapper::toCarOption)
            .toList();
    }

    @Override
    public void update(CarOption<Interior> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public void delete(CarOption<Interior> option) {
        Optional<InteriorOptionDao> dao = repository.findById(option.optionId());

        dao.ifPresent(o -> {
            InteriorOptionDao removedDao = InteriorOptionDao.builder()
                .modelId(o.getModelId())
                .interior(o.getInterior())
                .priceDelta(o.getPriceDelta())
                .updatedAt(o.getUpdatedAt())
                .createdAt(o.getCreatedAt())
                .removed(true)
                .build();

            repository.save(removedDao);
        });
    }
}