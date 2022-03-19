package uz.pdp.warehouse.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputProductDto {
    private Integer productId;
    private Double amount;
    private Double price;
    private Integer outputId;
}
