package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import org.springframework.data.repository.CrudRepository;

public interface TitlesRepository extends CrudRepository<Title, String> {
}
