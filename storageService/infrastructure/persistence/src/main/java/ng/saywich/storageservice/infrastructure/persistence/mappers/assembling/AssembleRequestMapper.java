package ng.saywich.storageservice.infrastructure.persistence.mappers.assembling;

import ng.saywich.storageservice.domain.assembling.AssembleRequest;
import ng.saywich.storageservice.infrastructure.persistence.models.assembling.AssembleRequestDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = WarehouseAdminMapper.class)
public interface AssembleRequestMapper {
    AssembleRequestDao toAssembleRequestDao(AssembleRequest request);

    AssembleRequest toAssembleRequest(AssembleRequestDao dao);
}
