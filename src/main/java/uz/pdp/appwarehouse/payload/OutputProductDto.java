package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.util.Date;

@Data
public class OutputProductDto {

    private Integer productId;
    private Double amount;
    private Double price;
    private Integer outputId;
}
