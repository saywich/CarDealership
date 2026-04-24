package ng.saywich.storageservice.application.services.stock;

import lombok.AllArgsConstructor;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.storageservice.application.abstractions.stock.IPartStockUnitRepository;
import ng.saywich.storageservice.application.contracts.stock.IPartStockUnitService;
import ng.saywich.storageservice.application.contracts.stock.operations.CreatePartStockUnit;
import ng.saywich.storageservice.application.contracts.stock.operations.DeletePartStockUnit;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryPartStockAvailability;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryPartStockUnitByPartId;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryPartStockUnits;
import ng.saywich.storageservice.application.contracts.stock.operations.UpdatePartStockUnit;
import ng.saywich.storageservice.domain.stock.PartStockUnit;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PartStockUnitService implements IPartStockUnitService {
    private IPartStockUnitRepository repository;

    @Override
    public QueryPartStockUnits.Response query(QueryPartStockUnits.Request request) {
        return new QueryPartStockUnits.Response(repository.query(request.unitIds()));
    }

    @Override
    public QueryPartStockUnitByPartId.Response queryByPartId(QueryPartStockUnitByPartId.Request request) {
        return new QueryPartStockUnitByPartId.Response(
            repository.queryByPartId(request.partId()).orElse(null));
    }

    @Override
    public QueryPartStockAvailability.Response queryAvailability(QueryPartStockAvailability.Request request) {
        return new QueryPartStockAvailability.Response(
            repository.isPartAvailable(request.partId()));
    }

    @Override
    public CreatePartStockUnit.Response create(CreatePartStockUnit.Request request) {
        repository.create(request.unit());

        return new CreatePartStockUnit.Response(request.unit());
    }

    @Override
    public UpdatePartStockUnit.Response update(UpdatePartStockUnit.Request request) {
        repository.update(request.unit());

        return new UpdatePartStockUnit.Response(request.unit());
    }

    @Override
    public DeletePartStockUnit.Response delete(DeletePartStockUnit.Request request) {
        PartStockUnit unit = repository.queryById(request.unitId()).orElse(null);

        if (unit == null) {
            throw new DomainValidationException("Can't find stock unit.");
        }

        repository.delete(request.unitId());

        return new DeletePartStockUnit.Response(unit);
    }
}
