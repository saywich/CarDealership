package ng.saywich.storageservice.infrastructure.persistence.abstractions.assembling;

import ng.saywich.storageservice.application.abstractions.assembling.IWarehouseAdminRepository;
import ng.saywich.storageservice.domain.assembling.WarehouseAdmin;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.assembling.IWarehouseAdminDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.assembling.WarehouseAdminMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.assembling.WarehouseAdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class WarehouseAdminRepository implements IWarehouseAdminRepository {
    @Autowired
    private IWarehouseAdminDaoRepository warehouseAdminDaoRepository;

    @Autowired
    private WarehouseAdminMapper warehouseAdminMapper;

    @Override
    public List<WarehouseAdmin> query(UUID[] adminIds) {
        List<WarehouseAdminDao> daoList = adminIds.length == 0
            ? warehouseAdminDaoRepository.findAll()
            : warehouseAdminDaoRepository.findAllById(Arrays.stream(adminIds).toList());

        return daoList.stream()
            .map(warehouseAdminMapper::toWarehouseAdmin)
            .toList();
    }

    @Override
    public Optional<WarehouseAdmin> queryById(UUID adminId) {
        return warehouseAdminDaoRepository.findById(adminId)
            .map(warehouseAdminMapper::toWarehouseAdmin);
    }

    @Override
    public void create(WarehouseAdmin admin) {
        warehouseAdminDaoRepository.save(
            warehouseAdminMapper.toWarehouseAdminDao(admin));
    }

    @Override
    public void update(WarehouseAdmin admin) {
        warehouseAdminDaoRepository.save(
            warehouseAdminMapper.toWarehouseAdminDao(admin));
    }

    @Override
    public void delete(UUID adminId) {
        Optional<WarehouseAdminDao> dao = warehouseAdminDaoRepository.findById(adminId);
        dao.ifPresent(b -> warehouseAdminDaoRepository.save(b.toBuilder()
            .removed(true).build()
        ));
    }
}
