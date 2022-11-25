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
public class CommonResponseModel implements Serializable {
    private boolean success;
    private String message;
    private String error;

    public static CommonResponseModel build(boolean success, String message, String error) {
        CommonResponseModel cR = new CommonResponseModel();
        cR.setSuccess(success);
        cR.setMessage(message);
        cR.setError(error);

        return cR;
    }
//    Việc chúng ta xây dựng method build với static bởi vì bản chất Spring không sử dụng cơ chế stateful (singleton).
//    Do vậy việc sử dụng static sẽ làm cho performance tăng lên trong quá trình triệu hồi thực thi.
//    Đây là một giải pháp kết hợp factory method để cập nhật thay đổi đối tượng đang có sẵn.
}
