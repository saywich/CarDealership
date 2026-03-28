package ng.saywich.application.services.carparts;

import ng.saywich.application.abstractions.carparts.IPartRepository;
import ng.saywich.domain.car.parts.Rims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RimsService extends PartService<Rims> {
    @Autowired
    private IPartRepository<Rims> rimsRepository;

    @Override
    protected IPartRepository<Rims> getRepository() {
        return rimsRepository;
    }
}
