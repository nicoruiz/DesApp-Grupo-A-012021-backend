package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateUserReviewDto extends CreateReviewDto{
    private boolean hasSpoiler;
    private String username;
    private String localization;

    public CreateUserReviewDto() {}
}
