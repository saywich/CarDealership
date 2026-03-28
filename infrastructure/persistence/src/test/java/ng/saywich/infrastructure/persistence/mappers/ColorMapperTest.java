package ng.saywich.infrastructure.persistence.mappers;

import ng.saywich.domain.car.parts.Color;
import ng.saywich.domain.car.parts.Engine;
import ng.saywich.infrastructure.persistence.mappers.parts.ColorMapper;
import ng.saywich.infrastructure.persistence.models.parts.ColorDao;
import ng.saywich.infrastructure.persistence.models.parts.EngineDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

public class ColorMapperTest {
    ColorMapper mapper = Mappers.getMapper(ColorMapper.class);

    @Test public void ColorMapsToColorDao_DaoConstructs() {
        Color color = new Color(UUID.randomUUID(), "magenta", 0xFFFFFF);

        ColorDao dao = mapper.toColorDao(color);

        Assertions.assertEquals(color.getPartId(), dao.getId());
        Assertions.assertEquals(color.name(), dao.getName());
        Assertions.assertEquals(color.hexCode(), dao.getHexCode());
    }

    @Test public void ColorDaoMapsToColor_ColorConstructs() {
        ColorDao dao = ColorDao.builder()
            .id(UUID.randomUUID())
            .name("magenta")
            .hexCode(0xFFFFFF)
            .build();

        Color color = mapper.toColor(dao);

        Assertions.assertEquals(color.getPartId(), dao.getId());
        Assertions.assertEquals(color.name(), dao.getName());
        Assertions.assertEquals(color.hexCode(), dao.getHexCode());
    }
}
