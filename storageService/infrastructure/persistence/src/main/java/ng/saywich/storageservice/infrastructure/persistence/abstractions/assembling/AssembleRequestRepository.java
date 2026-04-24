package ng.saywich.storageservice.infrastructure.persistence.abstractions.assembling;

import ng.saywich.storageservice.application.abstractions.assembling.IAssembleRequestRepository;
import ng.saywich.storageservice.domain.assembling.AssembleRequest;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.assembling.IAssembleRequestDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.assembling.AssembleRequestMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.assembling.AssembleRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AssembleRequestRepository implements IAssembleRequestRepository {
    @Autowired
    private IAssembleRequestDaoRepository assembleRequestDaoRepository;

    @Autowired
    private AssembleRequestMapper assembleRequestMapper;

    @Override
    public List<AssembleRequest> query(UUID[] requestIds) {
        List<AssembleRequestDao> daoList = requestIds.length == 0
            ? assembleRequestDaoRepository.findAll()
            : assembleRequestDaoRepository.findAllById(Arrays.stream(requestIds).toList());

        return daoList.stream()
            .map(assembleRequestMapper::toAssembleRequest)
            .toList();
    }

    @Override
    public Optional<AssembleRequest> queryById(UUID requestId) {
        return assembleRequestDaoRepository.findById(requestId)
            .map(assembleRequestMapper::toAssembleRequest);
    }

    @Override
    public void create(AssembleRequest request) {
        assembleRequestDaoRepository.save(
            assembleRequestMapper.toAssembleRequestDao(request));
    }

    @Override
    public void update(AssembleRequest request) {
        assembleRequestDaoRepository.save(
            assembleRequestMapper.toAssembleRequestDao(request));
    }

    @Override
    public void delete(UUID requestId) {
        Optional<AssembleRequestDao> dao = assembleRequestDaoRepository.findById(requestId);
        dao.ifPresent(b -> assembleRequestDaoRepository.save(b.toBuilder()
            .removed(true).build()
        ));
    }
}
