package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;

public interface SpecBuilder<T, B> {
    Specification<T> buildCriteriaSpecs(B params);
}
