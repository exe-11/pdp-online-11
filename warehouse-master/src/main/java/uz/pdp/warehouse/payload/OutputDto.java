package uz.pdp.warehouse.payload;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class OutputDto {
    private Timestamp date;
    private Integer warehouseId;
    private Integer clientId;
    private Integer currencyId;
    private String factureNumber;
    private String code;
}
