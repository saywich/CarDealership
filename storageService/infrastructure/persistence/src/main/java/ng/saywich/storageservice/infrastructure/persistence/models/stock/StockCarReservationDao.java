package ng.saywich.storageservice.infrastructure.persistence.models.stock;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.storageservice.infrastructure.persistence.models.BaseEntity;
import ng.saywich.storageservice.infrastructure.persistence.models.assembling.AssembleRequestDao;
import ng.saywich.storageservice.infrastructure.persistence.models.cars.CarDao;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "stock_car_reservations", schema = "stock")
public class StockCarReservationDao extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "assemble_request_id")
    private AssembleRequestDao assembleRequest;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarDao reservedCar;
}