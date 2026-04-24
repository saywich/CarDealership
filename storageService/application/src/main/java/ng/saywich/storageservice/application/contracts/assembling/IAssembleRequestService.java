package ng.saywich.storageservice.application.contracts.assembling;

import ng.saywich.storageservice.application.contracts.assembling.operations.CreateAssembleRequest;
import ng.saywich.storageservice.application.contracts.assembling.operations.DeleteAssembleRequest;
import ng.saywich.storageservice.application.contracts.assembling.operations.QueryAssembleRequests;
import ng.saywich.storageservice.application.contracts.assembling.operations.UpdateAssembleRequest;

public interface IAssembleRequestService {
    public QueryAssembleRequests.Response query(QueryAssembleRequests.Request request);

    public CreateAssembleRequest.Response create(CreateAssembleRequest.Request request);

    public UpdateAssembleRequest.Response update(UpdateAssembleRequest.Request request);

    public DeleteAssembleRequest.Response delete(DeleteAssembleRequest.Request request);
}
