package uz.pdp.warehouse.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private String name;
    private Integer parentCategoryId;
}
