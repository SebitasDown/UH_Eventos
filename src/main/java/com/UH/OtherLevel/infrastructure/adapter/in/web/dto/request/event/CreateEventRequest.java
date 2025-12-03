package com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event;

import com.UH.OtherLevel.infrastructure.adapter.in.web.validation.ValidEventDates;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@ValidEventDates(groups = {ValidationGroups.Create.class})
@Data
public class CreateEventRequest {
        @NotBlank(message = "{event.name.notblank}", groups = ValidationGroups.Create.class)
        @Size(min = 3, max = 100, message = "{event.name.size}", groups = ValidationGroups.Create.class)
        private String name;

        @Size(max = 500, message = "{event.description.size}", groups = ValidationGroups.Create.class)
        private String description;

        @NotNull(message = "{event.date.notnull}", groups = ValidationGroups.Create.class)
        @Future(message = "{event.date.future}", groups = ValidationGroups.Create.class)
        private LocalDateTime date;

        @NotNull(message = "{event.endDate.notnull}", groups = ValidationGroups.Create.class)
        @Future(message = "{event.endDate.future}", groups = ValidationGroups.Create.class)
        private LocalDateTime endDate;

        @NotNull(message = "{event.venue.notnull}", groups = ValidationGroups.Create.class)
        @Positive(message = "{event.venue.positive}", groups = ValidationGroups.Create.class)
        private Long venueId;
}
