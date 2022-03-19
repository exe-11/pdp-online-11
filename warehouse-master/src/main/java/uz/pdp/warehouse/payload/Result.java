package uz.pdp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String message;
    private boolean success;
    private Object data;

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
