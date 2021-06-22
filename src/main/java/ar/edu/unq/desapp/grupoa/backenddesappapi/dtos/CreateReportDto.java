package ar.edu.unq.desapp.grupoa.backenddesappapi.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateReportDto implements Serializable {
    private String comment;
}
