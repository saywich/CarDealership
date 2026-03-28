package ng.saywich.infrastructure.persistence.abstractions.parts;

import ng.saywich.application.abstractions.carparts.IPartRepository;
import ng.saywich.domain.car.parts.Transmission;
import ng.saywich.infrastructure.persistence.jparepositories.parts.ITransmissionDaoRepository;
import ng.saywich.infrastructure.persistence.mappers.parts.TransmissionMapper;
import ng.saywich.infrastructure.persistence.models.parts.InteriorDao;
import ng.saywich.infrastructure.persistence.models.parts.TransmissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TransmissionRepository implements IPartRepository<Transmission> {
    @Autowired
    private ITransmissionDaoRepository transmissionDaoRepository;

    @Autowired
    private TransmissionMapper mapper;

    @Override
    public void create(Transmission part) {
        transmissionDaoRepository.save(mapper.toTransmissionDao(part));
    }

    @Override
    public List<Transmission> query(UUID[] partIds) {
        List<TransmissionDao> daoList = partIds.length == 0
            ? transmissionDaoRepository.findAll()
            : transmissionDaoRepository.findAllById(List.of(partIds));

        return daoList.stream()
            .map(mapper::toTransmission)
            .toList();
    }

    @Override
    public void update(Transmission part) {
        transmissionDaoRepository.save(mapper.toTransmissionDao(part));
    }

    @Override
    public void delete(Transmission part) {
        transmissionDaoRepository.save(
            mapper.toTransmissionDao(part).toBuilder().removed(true).build()
        );
    }
}
