package ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.reviews;

import lombok.Data;

@Data
public class SearchReviewParamsDto {
    private String titleId;
    private String platform;
    private Boolean spoiler;
    private String type;
    private String language;
    private String localization;

    public SearchReviewParamsDto(String titleId, String platform, Boolean spoiler, String type, String language, String localization) {
        this.titleId = titleId;
        this.platform = platform;
        this.spoiler = spoiler;
        this.type = type;
        this.language = language;
        this.localization = localization;
    }

}
