package ng.saywich.storageservice.application.contracts.car;

import ng.saywich.storageservice.application.contracts.car.operations.*;

public interface ICarModelService {
    public CreateCarModel.Response create(CreateCarModel.Request request);

    public UpdateCarModel.Response update(UpdateCarModel.Request request);

    public DeleteCarModel.Response delete(DeleteCarModel.Request request);

    public GetCarModelDetails.Response getDetails(GetCarModelDetails.Request request);

    public QueryCarModels.Response query(QueryCarModels.Request request);

    public QueryCarModels.Response queryPage(
        QueryCarModels.Request request, int pageSize, int page);

    public FilterCarModels.Response queryWithFilter(
        FilterCarModels.Request request, int pageSize, int page);

    public ConfigureCarModel.Response configure(ConfigureCarModel.Request request);
}
