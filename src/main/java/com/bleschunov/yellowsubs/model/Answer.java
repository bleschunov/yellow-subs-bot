package com.bleschunov.yellowsubs.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bleschunov Dmitry
 */
@Getter
@Setter
@ToString
public class Answer {
    private long id;
    private String type;
    private String name;
    private String value;
}
