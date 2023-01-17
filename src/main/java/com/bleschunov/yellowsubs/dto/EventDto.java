package com.bleschunov.yellowsubs.dto;

import com.bleschunov.yellowsubs.model.AppDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bleschunov Dmitry
 */
@Getter
@Setter
@ToString
public class EventDto {
    @JsonProperty("id")
    private long timepadId;

    @JsonProperty("name")
    private String title;

    @JsonProperty("description_short")
    private String descriptionShort;

    private String url;

    @JsonProperty("starts_at")
    private AppDate startsAt;

    @JsonProperty("ends_at")
    private AppDate endsAt;

    @JsonProperty("created_at")
    private AppDate createdAt;
}
