package ng.saywich.storageservice.infrastructure.persistence.abstractions.stock;

import ng.saywich.storageservice.application.abstractions.stock.IStockPartReservationRepository;
import ng.saywich.storageservice.domain.stock.StockPartReservation;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.stock.IStockPartReservationDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.stock.StockPartReservationMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.stock.StockPartReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StockPartReservationRepository implements IStockPartReservationRepository {
    @Autowired
    private IStockPartReservationDaoRepository repository;

    @Autowired
    private StockPartReservationMapper mapper;

    public List<StockPartReservation> query(UUID[] ids) {
        List<StockPartReservationDao> daoList = ids.length == 0
            ? repository.findAll()
            : repository.findAllById(Arrays.stream(ids).toList());

        return daoList.stream().map(mapper::toStockPartReservation).toList();
    }

    public Optional<StockPartReservation> queryById(UUID id) {
        return repository.findById(id)
            .map(mapper::toStockPartReservation);
    }

    public List<StockPartReservation> queryByPartId(UUID partId) {
        return repository.findAllByPartStockUnit_PartId(partId).stream()
            .map(mapper::toStockPartReservation)
            .toList();
    }

    public List<StockPartReservation> queryByRequestId(UUID requestId) {
        return repository.findAllByAssembleRequest_Id(requestId).stream()
            .map(mapper::toStockPartReservation)
            .toList();
    }

    public void create(StockPartReservation reservation) {
        repository.save(mapper.toStockPartReservationDao(reservation));
    }

    public void update(StockPartReservation reservation) {
        repository.save(mapper.toStockPartReservationDao(reservation));
    }

    public void delete(UUID reservationId) {
        repository.findById(reservationId).ifPresent(dao ->
            repository.save(dao.toBuilder().removed(true).build()));
    }
}
