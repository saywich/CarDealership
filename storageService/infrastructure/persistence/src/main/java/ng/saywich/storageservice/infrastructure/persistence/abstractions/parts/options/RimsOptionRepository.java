package ng.saywich.storageservice.infrastructure.persistence.abstractions.parts.options;

import ng.saywich.storageservice.application.abstractions.carparts.IPartOptionRepository;
import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Rims;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.options.IRimsOptionDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options.RimsOptionMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.RimsOptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RimsOptionRepository implements IPartOptionRepository<Rims> {

    @Autowired
    private IRimsOptionDaoRepository repository;

    @Autowired
    private RimsOptionMapper mapper;

    @Override
    public void create(CarOption<Rims> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public List<CarOption<Rims>> query(UUID[] partIds) {
        return repository.findAllById(List.of(partIds)).stream()
            .map(mapper::toCarOption)
            .toList();
    }

    @Override
    public List<CarOption<Rims>> queryForModel(UUID modelId) {
        return repository.findByModelId(modelId).stream()
            .map(mapper::toCarOption)
            .toList();
    }

    @Override
    public void update(CarOption<Rims> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public void delete(CarOption<Rims> option) {
        Optional<RimsOptionDao> dao = repository.findById(option.optionId());

        dao.ifPresent(o -> {
            RimsOptionDao removedDao = RimsOptionDao.builder()
                .modelId(o.getModelId())
                .rims(o.getRims())
                .priceDelta(o.getPriceDelta())
                .updatedAt(o.getUpdatedAt())
                .createdAt(o.getCreatedAt())
                .removed(true)
                .build();

            repository.save(removedDao);
        });
    }
}