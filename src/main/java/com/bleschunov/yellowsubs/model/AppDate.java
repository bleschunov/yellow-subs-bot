package com.bleschunov.yellowsubs.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bleschunov Dmitry
 */
@Setter
@Getter
@ToString
public class AppDate {
    private String date;
    private int timezoneType;
    private String timezone;
}
