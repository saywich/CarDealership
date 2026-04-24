package ng.saywich.storageservice.application.services.assembling;

import lombok.AllArgsConstructor;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.shared.domain.exceptions.InvalidValue;
import ng.saywich.storageservice.application.abstractions.assembling.IAssembleRequestRepository;
import ng.saywich.storageservice.application.contracts.assembling.IAssembleRequestService;
import ng.saywich.storageservice.application.contracts.assembling.operations.CreateAssembleRequest;
import ng.saywich.storageservice.application.contracts.assembling.operations.DeleteAssembleRequest;
import ng.saywich.storageservice.application.contracts.assembling.operations.QueryAssembleRequests;
import ng.saywich.storageservice.application.contracts.assembling.operations.UpdateAssembleRequest;
import ng.saywich.storageservice.domain.assembling.AssembleRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssembleRequestService implements IAssembleRequestService {
    private IAssembleRequestRepository repository;

    @Override
    public QueryAssembleRequests.Response query(QueryAssembleRequests.Request request) {
        return new QueryAssembleRequests.Response(repository.query(request.requestIds()));
    }

    @Override
    public CreateAssembleRequest.Response create(CreateAssembleRequest.Request request) {
        repository.create(request.request());

        return new CreateAssembleRequest.Response(request.request());
    }

    @Override
    public UpdateAssembleRequest.Response update(UpdateAssembleRequest.Request request) {
        repository.update(request.request());

        return new UpdateAssembleRequest.Response(request.request());
    }

    @Override
    public DeleteAssembleRequest.Response delete(DeleteAssembleRequest.Request request) {
        AssembleRequest assembleRequest = repository.queryById(request.requestId()).orElse(null);

        if (assembleRequest == null) {
            throw new DomainValidationException("Can't find request.");
        }

        repository.delete(request.requestId());

        return new DeleteAssembleRequest.Response(assembleRequest);
    }
}
