package ng.saywich.infrastructure.persistence.abstractions.parts;

import ng.saywich.application.abstractions.carparts.IPartRepository;
import ng.saywich.domain.car.parts.Color;
import ng.saywich.infrastructure.persistence.jparepositories.parts.IColorDaoRepository;
import ng.saywich.infrastructure.persistence.mappers.parts.ColorMapper;
import ng.saywich.infrastructure.persistence.models.parts.ColorDao;
import ng.saywich.infrastructure.persistence.models.parts.EngineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ColorRepository implements IPartRepository<Color> {
    @Autowired
    private IColorDaoRepository colorDaoRepository;

    @Autowired
    private ColorMapper mapper;

    @Override
    public void create(Color part) {
        colorDaoRepository.save(mapper.toColorDao(part));
    }

    @Override
    public List<Color> query(UUID[] partIds) {
        List<ColorDao> daoList = partIds.length == 0
            ? colorDaoRepository.findAll()
            : colorDaoRepository.findAllById(List.of(partIds));

        return daoList.stream()
            .map(mapper::toColor)
            .toList();
    }

    @Override
    public void update(Color part) {
        colorDaoRepository.save(mapper.toColorDao(part));
    }

    @Override
    public void delete(Color part) {
        colorDaoRepository.save(
            mapper.toColorDao(part).toBuilder().removed(true).build()
        );
    }
}