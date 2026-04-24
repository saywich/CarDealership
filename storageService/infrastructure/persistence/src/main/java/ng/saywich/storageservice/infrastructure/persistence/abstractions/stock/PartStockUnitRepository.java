package ng.saywich.storageservice.infrastructure.persistence.abstractions.stock;

import ng.saywich.storageservice.application.abstractions.stock.IPartStockUnitRepository;
import ng.saywich.storageservice.domain.stock.PartStockUnit;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.stock.IPartStockUnitDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.stock.PartStockUnitMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.stock.PartStockUnitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PartStockUnitRepository implements IPartStockUnitRepository {
    @Autowired
    private IPartStockUnitDaoRepository repository;

    @Autowired
    private PartStockUnitMapper mapper;

    @Override
    public List<PartStockUnit> query(UUID[] unitIds) {
        List<PartStockUnitDao> daoList = unitIds.length == 0
            ? repository.findAll()
            : repository.findAllById(Arrays.stream(unitIds).toList());

        return daoList.stream().map(mapper::toPartStockUnit).toList();
    }

    @Override
    public Optional<PartStockUnit> queryById(UUID unitId) {
        return repository.findById(unitId).map(mapper::toPartStockUnit);
    }

    @Override
    public Optional<PartStockUnit> queryByPartId(UUID partId) {
        return repository.findByPartId(partId).map(mapper::toPartStockUnit);
    }

    @Override
    public void create(PartStockUnit unit) {
        repository.save(mapper.toPartStockUnitDao(unit));
    }

    @Override
    public void update(PartStockUnit unit) {
        repository.save(mapper.toPartStockUnitDao(unit));
    }

    @Override
    public void delete(UUID unitId) {
        repository.findById(unitId).ifPresent(dao ->
            repository.save(dao.toBuilder().removed(true).build()));
    }

    @Override
    public boolean isPartAvailable(UUID partId) {
        return repository.findByPartId(partId)
            .map(unit -> unit.getTotalCount() > unit.getReservedCount())
            .orElse(false);
    }
}

