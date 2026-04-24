package ng.saywich.storageservice.infrastructure.persistence.abstractions.car;

import ng.saywich.shared.domain.car.AvailableOptions;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.options.*;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CarOptionsRepository {
    @Autowired
    private IBodyTypeOptionDaoRepository bodyTypeOptionDaoRepository;

    @Autowired
    private IColorOptionDaoRepository colorOptionDaoRepository;

    @Autowired
    private IEngineOptionDaoRepository engineOptionDaoRepository;

    @Autowired
    private IInteriorOptionDaoRepository interiorOptionDaoRepository;

    @Autowired
    private IRimsOptionDaoRepository rimsOptionDaoRepository;

    @Autowired
    private ITransmissionOptionDaoRepository transmissionOptionDaoRepository;

    @Autowired
    private BodyTypeOptionMapper bodyTypeOptionMapper;

    @Autowired
    private ColorOptionMapper colorOptionMapper;

    @Autowired
    private EngineOptionMapper engineOptionMapper;

    @Autowired
    private InteriorOptionMapper interiorOptionMapper;

    @Autowired
    private RimsOptionMapper rimsOptionMapper;

    @Autowired
    private TransmissionOptionMapper transmissionOptionMapper;

    public AvailableOptions getAvailableOptionsForModel(UUID modelId) {
        return new AvailableOptions(
            engineOptionDaoRepository.findByModelId(modelId).stream()
                .map(m -> engineOptionMapper.toCarOption(m))
                .toList(),
            transmissionOptionDaoRepository.findByModelId(modelId).stream()
                .map(m -> transmissionOptionMapper.toCarOption(m))
                .toList(),
            bodyTypeOptionDaoRepository.findByModelId(modelId).stream()
                .map(m -> bodyTypeOptionMapper.toCarOption(m))
                .toList(),
            rimsOptionDaoRepository.findByModelId(modelId).stream()
                .map(m -> rimsOptionMapper.toCarOption(m))
                .toList(),
            interiorOptionDaoRepository.findByModelId(modelId).stream()
                .map(m -> interiorOptionMapper.toCarOption(m))
                .toList(),
            colorOptionDaoRepository.findByModelId(modelId).stream()
                .map(m -> colorOptionMapper.toCarOption(m))
                .toList()
        );
    }
}
