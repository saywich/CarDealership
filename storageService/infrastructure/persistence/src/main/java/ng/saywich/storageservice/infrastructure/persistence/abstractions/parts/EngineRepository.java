package ng.saywich.storageservice.infrastructure.persistence.abstractions.parts;

import ng.saywich.storageservice.application.abstractions.carparts.IPartRepository;
import ng.saywich.shared.domain.car.parts.Engine;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.IEngineDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.EngineMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.EngineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class EngineRepository implements IPartRepository<Engine> {
    @Autowired
    private IEngineDaoRepository engineDaoRepository;

    @Autowired
    private EngineMapper mapper;

    @Override
    public void create(Engine part) {
        engineDaoRepository.save(mapper.toEngineDao(part));
    }

    @Override
    public List<Engine> query(UUID[] partIds) {
        List<EngineDao> engineDaoList = partIds.length == 0
            ? engineDaoRepository.findAll()
            : engineDaoRepository.findAllById(List.of(partIds));

        return engineDaoList.stream()
            .map(mapper::toEngine)
            .toList();
    }

    @Override
    public void update(Engine part) {
        engineDaoRepository.save(mapper.toEngineDao(part));
    }

    @Override
    public void delete(Engine part) {
        engineDaoRepository.save(
            mapper.toEngineDao(part).toBuilder().removed(true).build()
        );
    }
}
