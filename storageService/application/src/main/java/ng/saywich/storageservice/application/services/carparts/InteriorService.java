package ng.saywich.storageservice.application.services.carparts;

import ng.saywich.storageservice.application.abstractions.carparts.IPartRepository;
import ng.saywich.shared.domain.car.parts.Interior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteriorService extends PartService<Interior> {
    @Autowired
    private IPartRepository<Interior> interiorRepository;

    @Override
    protected IPartRepository<Interior> getRepository() {
        return interiorRepository;
    }
}
