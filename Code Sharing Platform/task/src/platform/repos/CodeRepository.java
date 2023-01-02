package platform.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.dtos.CodeDto;

import java.util.List;

@Repository
public interface CodeRepository extends CrudRepository<CodeDto, String> {

    List<CodeDto> getAllByTimeIsLimitedFalseAndViewsAreLimitedFalse();
}
