package ng.saywich.storageservice.application.services.assembling;

import lombok.AllArgsConstructor;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.storageservice.application.abstractions.assembling.IWarehouseAdminRepository;
import ng.saywich.storageservice.application.contracts.assembling.IWarehouseAdminService;
import ng.saywich.storageservice.application.contracts.assembling.operations.CreateWarehouseAdmin;
import ng.saywich.storageservice.application.contracts.assembling.operations.DeleteWarehouseAdmin;
import ng.saywich.storageservice.application.contracts.assembling.operations.QueryWarehouseAdmins;
import ng.saywich.storageservice.application.contracts.assembling.operations.UpdateWarehouseAdmin;
import ng.saywich.storageservice.domain.assembling.WarehouseAdmin;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WarehouseAdminService implements IWarehouseAdminService {
    private IWarehouseAdminRepository repository;

    @Override
    public QueryWarehouseAdmins.Response query(QueryWarehouseAdmins.Request request) {
        return new QueryWarehouseAdmins.Response(repository.query(request.adminIds()));
    }

    @Override
    public CreateWarehouseAdmin.Response create(CreateWarehouseAdmin.Request request) {
        repository.create(request.admin());

        return new CreateWarehouseAdmin.Response(request.admin());
    }

    @Override
    public UpdateWarehouseAdmin.Response update(UpdateWarehouseAdmin.Request request) {
        repository.update(request.admin());

        return new UpdateWarehouseAdmin.Response(request.admin());
    }

    @Override
    public DeleteWarehouseAdmin.Response delete(DeleteWarehouseAdmin.Request request) {
        WarehouseAdmin admin = repository.queryById(request.adminId()).orElse(null);

        if (admin == null) {
            throw new DomainValidationException("Can't find warehouse admin.");
        }

        repository.delete(request.adminId());

        return new DeleteWarehouseAdmin.Response(admin);
    }
}
