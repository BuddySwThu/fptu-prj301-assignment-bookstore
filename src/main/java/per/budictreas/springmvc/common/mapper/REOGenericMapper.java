package per.budictreas.springmvc.common.mapper;

import java.util.List;

public interface REOGenericMapper<S, D> {
    D toREO(S dto);

    S toDTO(D responseModel);

    List<D> toREO(List<S> dtos);

    List<S> toDTO(List<D> responseModels);
}
