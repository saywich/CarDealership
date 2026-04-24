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

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "stock_part_reservations", schema = "stock")
public class StockPartReservationDao extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "assemble_request_id")
    private AssembleRequestDao assembleRequest;

    @ManyToOne
    @JoinColumn(name = "part_stock_unit_id")
    private PartStockUnitDao partStockUnit;
}