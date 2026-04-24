package ng.saywich.storageservice.application.abstractions.assembling;

import ng.saywich.storageservice.domain.assembling.AssembleRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAssembleRequestRepository {
    List<AssembleRequest> query(UUID[] requestIds);

    Optional<AssembleRequest> queryById(UUID requestId);

    void create(AssembleRequest request);

    void update(AssembleRequest request);

    void delete(UUID requestId);
}
