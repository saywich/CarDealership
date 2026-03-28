package ng.saywich.application.services.carparts;

import ng.saywich.application.abstractions.carparts.IPartRepository;
import ng.saywich.domain.car.parts.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineService extends PartService<Engine> {
    @Autowired
    IPartRepository<Engine> engineRepository;

    @Override
    protected IPartRepository<Engine> getRepository() {
        return engineRepository;
    }
}
