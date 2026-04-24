package ng.saywich.storageservice.infrastructure.persistence.abstractions.parts;

import ng.saywich.storageservice.application.abstractions.carparts.IOptionSetRepository;
import ng.saywich.shared.domain.car.parts.OptionSet;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.options.IOptionSetDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.cars.OptionSetMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.cars.OptionSetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OptionSetRepository implements IOptionSetRepository {
    @Autowired
    private IOptionSetDaoRepository optionSetDaoRepository;

    @Autowired
    private OptionSetMapper optionSetMapper;

    @Override
    public List<OptionSet> query(UUID[] optionSetIds) {
        List<OptionSetDao> optionSetDaoList = optionSetIds.length == 0
            ? optionSetDaoRepository.findAll()
            : optionSetDaoRepository.findAllById(Arrays.stream(optionSetIds).toList());

        return optionSetDaoList.stream()
            .map(optionSetMapper::toOptionSet)
            .toList();
    }

    @Override
    public void create(OptionSet optionSet) {
        optionSetDaoRepository.save(optionSetMapper.toOptionSetDao(optionSet));
    }

    @Override
    public void update(OptionSet optionSet) {
        optionSetDaoRepository.save(optionSetMapper.toOptionSetDao(optionSet));
    }

    @Override
    public void delete(UUID optionSetId) {
        Optional<OptionSetDao> optionSet = optionSetDaoRepository
            .findById(optionSetId);

        optionSet.ifPresent((os) -> {
            optionSetDaoRepository.save(
                os.toBuilder().removed(true).build()
            );
        });
    }
}
