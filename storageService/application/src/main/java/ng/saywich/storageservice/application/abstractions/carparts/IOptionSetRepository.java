package ng.saywich.storageservice.application.abstractions.carparts;

import ng.saywich.shared.domain.car.parts.OptionSet;

import java.util.List;
import java.util.UUID;

public interface IOptionSetRepository {
    public List<OptionSet> query(UUID[] optionSetIds);

    public void create(OptionSet optionSet);

    public void update(OptionSet optionSet);

    public void delete(UUID optionSetId);
}
