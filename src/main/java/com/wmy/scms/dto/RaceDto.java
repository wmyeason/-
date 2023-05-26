package com.wmy.scms.dto;

import com.wmy.scms.entity.Race;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RaceDto implements Serializable {
    private List<Race> Sixteen;

    private List<Race> Quarter;

    private List<Race> Semi;

    private List<Race> Final;


}
