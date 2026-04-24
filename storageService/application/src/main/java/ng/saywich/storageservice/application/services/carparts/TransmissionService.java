package ng.saywich.storageservice.application.services.carparts;

import ng.saywich.storageservice.application.abstractions.carparts.IPartRepository;
import ng.saywich.shared.domain.car.parts.Transmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransmissionService extends PartService<Transmission> {
    @Autowired
    private IPartRepository<Transmission> transmissionRepository;

    @Override
    protected IPartRepository<Transmission> getRepository() {
        return transmissionRepository;
    }
}
