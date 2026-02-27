package ng.saywich.application.contracts.car;

import ng.saywich.application.contracts.car.operations.*;

public interface ICarModelService {
    public CreateCarModel.Response create(CreateCarModel.Request request);

    public UpdateCarModel.Response update(UpdateCarModel.Request request);

    public DeleteCarModel.Response delete(DeleteCarModel.Request request);

    public GetCarModelDetails.Response getDetails(GetCarModelDetails.Request request);

    public QueryCarModels.Response query(QueryCarModels.Request request);

    public FilterCarModels.Response queryWithFilter(FilterCarModels.Request request);

    public ConfigureCarModel.Response configure(ConfigureCarModel.Request request);
}
