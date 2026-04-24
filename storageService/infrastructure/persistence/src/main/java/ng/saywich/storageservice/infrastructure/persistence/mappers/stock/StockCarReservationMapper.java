package ng.saywich.storageservice.infrastructure.persistence.mappers.stock;

import ng.saywich.storageservice.domain.stock.StockCarReservation;
import ng.saywich.storageservice.infrastructure.persistence.mappers.assembling.AssembleRequestMapper;
import ng.saywich.storageservice.infrastructure.persistence.mappers.cars.CarMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.stock.StockCarReservationDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {AssembleRequestMapper.class, CarMapper.class}
)
public interface StockCarReservationMapper {
    StockCarReservationDao toStockCarReservationDao(StockCarReservation reservation);

    StockCarReservation toStockCarReservation(StockCarReservationDao dao);
}