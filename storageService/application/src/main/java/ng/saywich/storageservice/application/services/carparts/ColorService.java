package ng.saywich.storageservice.application.services.carparts;

import ng.saywich.storageservice.application.abstractions.carparts.IPartRepository;
import ng.saywich.shared.domain.car.parts.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorService extends PartService<Color> {
    @Autowired
    private IPartRepository<Color> colorRepository;

    @Override
    protected IPartRepository<Color> getRepository() {
        return colorRepository;
    }
}
