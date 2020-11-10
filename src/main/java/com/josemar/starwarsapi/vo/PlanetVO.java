package com.josemar.starwarsapi.vo;

import dev.swapi.vo.Film;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "planets")
public class PlanetVO {

    @Id
    @EqualsAndHashCode.Exclude
    private String id;

    @ApiModelProperty("Nome do Planeta")
    @NotBlank(message = "O campo Name é obrigatorio")
    private String name;

    @ApiModelProperty("Clima do Planeta")
    @NotBlank(message = "O campo Clime é obrigatorio")
    private String clime;

    @ApiModelProperty("Terreno do Planeta")
    @NotBlank(message = "O campo Terrain é obrigatorio")
    private String terrain;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty("Fimes que o Planeta aparece")
    private List<Film> films;

}
