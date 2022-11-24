package per.budictreas.springmvc.common.mapper;

import java.util.List;

public interface ResponseModelGenericMapper<S, D> {
    D toResponseModel(S dto);

    S toDTO(D responseModel);

    List<D> toResponseModel(List<S> dtos);

    List<S> toDTO(List<D> responseModels);
}
