package ng.saywich.infrastructure.persistence.abstractions.parts;

import ng.saywich.application.abstractions.carparts.IPartRepository;
import ng.saywich.domain.car.parts.Rims;
import ng.saywich.infrastructure.persistence.jparepositories.parts.IRimsDaoRepository;
import ng.saywich.infrastructure.persistence.mappers.parts.RimsMapper;
import ng.saywich.infrastructure.persistence.models.parts.InteriorDao;
import ng.saywich.infrastructure.persistence.models.parts.RimsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RimsRepository implements IPartRepository<Rims> {
    @Autowired
    private IRimsDaoRepository rimsDaoRepository;

    @Autowired
    private RimsMapper mapper;

    @Override
    public void create(Rims part) {
        rimsDaoRepository.save(mapper.toRimsDao(part));
    }

    @Override
    public List<Rims> query(UUID[] partIds) {
        List<RimsDao> daoList = partIds.length == 0
            ? rimsDaoRepository.findAll()
            : rimsDaoRepository.findAllById(List.of(partIds));

        return daoList.stream()
            .map(mapper::toRims)
            .toList();
    }

    @Override
    public void update(Rims part) {
        rimsDaoRepository.save(mapper.toRimsDao(part));
    }

    @Override
    public void delete(Rims part) {
        rimsDaoRepository.save(
            mapper.toRimsDao(part).toBuilder().removed(true).build()
        );
    }
}