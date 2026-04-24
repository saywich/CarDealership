package ng.saywich.storageservice.infrastructure.persistence.mappers.stock;

import ng.saywich.storageservice.domain.stock.StockPartReservation;
import ng.saywich.storageservice.infrastructure.persistence.mappers.assembling.AssembleRequestMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.stock.StockPartReservationDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {AssembleRequestMapper.class, PartStockUnitMapper.class}
)
public interface StockPartReservationMapper {
    StockPartReservationDao toStockPartReservationDao(StockPartReservation reservation);

    StockPartReservation toStockPartReservation(StockPartReservationDao dao);
}
