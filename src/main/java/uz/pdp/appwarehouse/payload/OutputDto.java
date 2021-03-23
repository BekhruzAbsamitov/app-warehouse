package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputDto {
    private Timestamp timestamp;
    private Integer warehouseId;
    private Integer clientId;
    private Integer currencyId;
    private String code;
}
