package hello;

import org.springframework.data.repository.CrudRepository;

public interface ThirdPartyApiRepository extends CrudRepository<ThirdPartyApi, Long>
{
}