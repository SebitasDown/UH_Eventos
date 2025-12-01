package com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateEventRequest {

        @NotBlank(message = "{event.name.notblank}", groups = ValidationGroups.Update.class)
        @Size(min = 3, max = 100, message = "{event.name.size}", groups = ValidationGroups.Update.class)
        private String name;

        @Size(max = 500, message = "{event.description.size}", groups = ValidationGroups.Update.class)
        private String description;

        @NotNull(message = "{event.date.notnull}", groups = ValidationGroups.Update.class)
        @Future(message = "{event.date.future}", groups = ValidationGroups.Update.class)
        private LocalDateTime date;

        @NotNull(message = "{event.endDate.notnull}", groups = ValidationGroups.Update.class)
        @Future(message = "{event.endDate.future}", groups = ValidationGroups.Update.class)
        private LocalDateTime endDate;

        @NotNull(message = "{event.venue.notnull}", groups = ValidationGroups.Update.class)
        @Positive(message = "{event.venue.positive}", groups = ValidationGroups.Update.class)
        private Long venueId;
}
