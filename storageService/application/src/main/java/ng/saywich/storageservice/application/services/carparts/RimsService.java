package ng.saywich.storageservice.application.services.carparts;

import ng.saywich.storageservice.application.abstractions.carparts.IPartRepository;
import ng.saywich.shared.domain.car.parts.Rims;
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
