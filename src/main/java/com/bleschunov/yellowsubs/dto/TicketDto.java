package com.bleschunov.yellowsubs.dto;

import com.bleschunov.yellowsubs.model.Answer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Getter
@Setter
@ToString
public class TicketDto {
    @JsonProperty("id")
    private String timepadId;

    @JsonProperty("email")
    private String ownerTimepadEmail;

    @JsonProperty("event_id")
    private long eventId;

    @JsonProperty("status_raw")
    private String status;

    private List<Answer> answers;

    @JsonProperty("reg_date")
    private String createdAt;
}
