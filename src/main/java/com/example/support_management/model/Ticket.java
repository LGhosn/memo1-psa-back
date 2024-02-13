package com.example.support_management.model;
import com.example.support_management.enums.Priority;
import com.example.support_management.enums.Severity;
import com.example.support_management.enums.State;
import com.example.support_management.enums.TypeOfProblem;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TICKETS")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TICKET")
    public Integer id;

    @Column(name = "TITLE")
    public String title;

    @Column(name = "DESCRIPTION")
    public String description;

    @Column(name = "STATE")
    public State state;

    @Column(name = "PRIORITY")
    public Priority priority;

    @Column(name = "SEVERITY")
    public Severity severity;

    @Column(name = "TYPE_OF_PROBLEM")
    public TypeOfProblem type;

    @Column(name = "CREATION_DATE")
    public LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "CLOSE_DATE")
    public LocalDateTime closeDate;

    public void setPriority(Priority priority) { this.priority = priority; }

    public void setState(State state) { this.state = state; }

    public void setSeverity(Severity severity) { this.severity = severity; }

    public Priority getPriority() { return this.priority; }

    public State getState() { return this.state; }

    public Severity getSeverity() { return this.severity; }
}
