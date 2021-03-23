package uz.pdp.appwarehouse.payload;

import lombok.Data;
import uz.pdp.appwarehouse.entity.Supplier;

import java.sql.Timestamp;

@Data
public class InputDto {
    private Timestamp timestamp;
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String code;
}
