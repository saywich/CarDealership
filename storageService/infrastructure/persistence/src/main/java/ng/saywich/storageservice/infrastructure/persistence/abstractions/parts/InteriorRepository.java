package ng.saywich.storageservice.infrastructure.persistence.abstractions.parts;

import ng.saywich.storageservice.application.abstractions.carparts.IPartRepository;
import ng.saywich.shared.domain.car.parts.Interior;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.IInteriorDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.InteriorMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.InteriorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class InteriorRepository implements IPartRepository<Interior> {
    @Autowired
    private IInteriorDaoRepository interiorDaoRepository;

    @Autowired
    private InteriorMapper mapper;

    @Override
    public void create(Interior part) {
        interiorDaoRepository.save(mapper.toInteriorDao(part));
    }

    @Override
    public List<Interior> query(UUID[] partIds) {
        List<InteriorDao> daoList = partIds.length == 0
            ? interiorDaoRepository.findAll()
            : interiorDaoRepository.findAllById(List.of(partIds));

        return daoList.stream()
            .map(mapper::toInterior)
            .toList();
    }

    @Override
    public void update(Interior part) {
        interiorDaoRepository.save(mapper.toInteriorDao(part));
    }

    @Override
    public void delete(Interior part) {
        interiorDaoRepository.save(
            mapper.toInteriorDao(part).toBuilder().removed(true).build()
        );
    }
}
