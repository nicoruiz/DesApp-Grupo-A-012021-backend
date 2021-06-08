package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long>, JpaSpecificationExecutor<Review> {
}
