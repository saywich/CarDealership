package ng.saywich.storageservice.infrastructure.persistence.abstractions.parts.options;

import ng.saywich.storageservice.application.abstractions.carparts.IPartOptionRepository;
import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Transmission;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.options.ITransmissionOptionDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options.TransmissionOptionMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.TransmissionOptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TransmissionOptionRepository implements IPartOptionRepository<Transmission> {
    @Autowired
    private ITransmissionOptionDaoRepository repository;

    @Autowired
    private TransmissionOptionMapper mapper;

    @Override
    public void create(CarOption<Transmission> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public List<CarOption<Transmission>> query(UUID[] partIds) {
        return repository.findAllById(List.of(partIds)).stream()
            .map(mapper::toCarOption)
            .toList();
    }

    @Override
    public List<CarOption<Transmission>> queryForModel(UUID modelId) {
        return repository.findByModelId(modelId).stream()
            .map(mapper::toCarOption)
            .toList();
    }

    @Override
    public void update(CarOption<Transmission> option) {
        repository.save(mapper.toOptionDao(option));
    }

    @Override
    public void delete(CarOption<Transmission> option) {
        Optional<TransmissionOptionDao> dao = repository.findById(option.optionId());

        dao.ifPresent(o -> {
            TransmissionOptionDao removedDao = TransmissionOptionDao.builder()
                .modelId(o.getModelId())
                .transmission(o.getTransmission())
                .priceDelta(o.getPriceDelta())
                .updatedAt(o.getUpdatedAt())
                .createdAt(o.getCreatedAt())
                .removed(true)
                .build();

            repository.save(removedDao);
        });
    }
}