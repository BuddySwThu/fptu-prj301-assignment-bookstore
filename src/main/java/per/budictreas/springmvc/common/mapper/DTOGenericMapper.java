package per.budictreas.springmvc.common.mapper;

import java.util.List;

public interface DTOGenericMapper<S, D> { //S: Source class (Entity)  -  D: Destination class (DTO)
    D toDTO(S entity);

    S toEntity(D dto);

    List<D> toDTO(List<S> entities);

    List<S> toEntity(List<D> dtos);
}
