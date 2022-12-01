package per.budictreas.springmvc.data.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartProductResponseModel implements Serializable {
    private int id;
    private String name;
    private float price;
    private int quantity;
}
