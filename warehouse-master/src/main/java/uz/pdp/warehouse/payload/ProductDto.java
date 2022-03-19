package uz.pdp.warehouse.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String name;
    private Integer categoryId;
    private Integer photoId;
    private Integer measurementId;
}
