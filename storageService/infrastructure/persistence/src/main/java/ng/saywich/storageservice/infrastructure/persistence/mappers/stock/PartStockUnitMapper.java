package ng.saywich.storageservice.infrastructure.persistence.mappers.stock;

import ng.saywich.storageservice.domain.stock.PartStockUnit;
import ng.saywich.storageservice.infrastructure.persistence.models.stock.PartStockUnitDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PartStockUnitMapper {
    PartStockUnitDao toPartStockUnitDao(PartStockUnit unit);

    PartStockUnit toPartStockUnit(PartStockUnitDao dao);
}