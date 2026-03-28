package ng.saywich.infrastructure.persistence.abstractions.purchasing;

import ng.saywich.application.abstractions.order.IManagerRepository;
import ng.saywich.domain.purchasing.Manager;
import ng.saywich.infrastructure.persistence.jparepositories.purchasing.IManagerDaoRepository;
import ng.saywich.infrastructure.persistence.mappers.purchasing.ManagerMapper;
import ng.saywich.infrastructure.persistence.models.purchasing.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ManagerRepository implements IManagerRepository {
    @Autowired
    private IManagerDaoRepository managerDaoRepository;

    @Autowired
    private ManagerMapper mapper;

    @Override
    public List<Manager> query(UUID[] ids) {
        List<ManagerDao> managerDaoList = ids.length == 0
            ? managerDaoRepository.findAll()
            : managerDaoRepository.findAllById(List.of(ids));

        return managerDaoList.stream()
            .map(mapper::toManager)
            .toList();
    }

    @Override
    public Manager create(Manager manager) {
        return mapper.toManager(
            managerDaoRepository.save(mapper.toManagerDao(manager)));
    }

    @Override
    public Manager update(Manager manager) {
        return mapper.toManager(
            managerDaoRepository.save(mapper.toManagerDao(manager)));
    }

    @Override
    public Manager delete(UUID id) {
        Optional<ManagerDao> dao = managerDaoRepository.findById(id);

        if (dao.isPresent()) {
            ManagerDao removedDao = dao.get().toBuilder().removed(true).build();
            managerDaoRepository.save(removedDao);

            return mapper.toManager(removedDao);
        }

        return null;
    }
}
