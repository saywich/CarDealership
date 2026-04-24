package ng.saywich.storageservice.infrastructure.persistence.abstractions.stock;

import ng.saywich.storageservice.application.abstractions.stock.IStockCarReservationRepository;
import ng.saywich.storageservice.domain.stock.StockCarReservation;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.stock.IStockCarReservationDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.stock.StockCarReservationMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.stock.StockCarReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StockCarReservationRepository implements IStockCarReservationRepository {
    @Autowired
    private IStockCarReservationDaoRepository repository;

    @Autowired
    private StockCarReservationMapper mapper;

    public List<StockCarReservation> query(UUID[] ids) {
        List<StockCarReservationDao> daoList = ids.length == 0
            ? repository.findAll()
            : repository.findAllById(Arrays.stream(ids).toList());

        return daoList.stream().map(mapper::toStockCarReservation).toList();
    }

    public Optional<StockCarReservation> queryById(UUID id) {
        return repository.findById(id)
            .map(mapper::toStockCarReservation);
    }

    public Optional<StockCarReservation> queryByCarId(UUID carId) {
        return repository.findByReservedCar_Id(carId)
            .map(mapper::toStockCarReservation);
    }

    public Optional<StockCarReservation> queryByRequestId(UUID requestId) {
        return repository.findByAssembleRequest_Id(requestId)
            .map(mapper::toStockCarReservation);
    }

    public void create(StockCarReservation reservation) {
        repository.save(mapper.toStockCarReservationDao(reservation));
    }

    public void update(StockCarReservation reservation) {
        repository.save(mapper.toStockCarReservationDao(reservation));
    }

    public void delete(UUID reservationId) {
        repository.findById(reservationId).ifPresent(dao ->
            repository.save(dao.toBuilder().removed(true).build()));
    }

    @Override
    public boolean isCarAvailable(UUID carId) {
        return repository.findByReservedCar_Id(carId).isEmpty();
    }
}
