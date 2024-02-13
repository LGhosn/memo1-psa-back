package com.example.support_management.model.dto;

import com.example.support_management.enums.Priority;
import com.example.support_management.enums.Severity;
import com.example.support_management.enums.State;
import com.example.support_management.enums.TypeOfProblem;

import java.time.LocalDateTime;

public class TicketDTO {
    public Integer id;
    public String title;
    public Priority priority;
    public State state;
    public Severity severity;
    public TypeOfProblem type;
    public LocalDateTime creationDate;
    public LocalDateTime closeDate;

    public TicketDTO(
            Integer id,
            String title,
            Priority priority,
            State state,
            Severity severity,
            TypeOfProblem typeOfProblem,
            LocalDateTime creationDate,
            LocalDateTime closeDate
    ) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.state = state;
        this.severity = severity;
        this.type = typeOfProblem;
        this.creationDate = creationDate;
        this.closeDate = closeDate;
    }
}
