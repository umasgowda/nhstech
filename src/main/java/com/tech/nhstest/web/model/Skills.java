package com.tech.nhstest.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Model responsible for holding skills data.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Skills implements Serializable {
    private String level;

    private List<String> skillsList = new ArrayList<>();
}
