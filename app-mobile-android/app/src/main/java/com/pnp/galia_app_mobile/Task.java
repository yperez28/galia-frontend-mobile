package com.pnp.galia_app_mobile;

import androidx.annotation.NonNull;

import java.util.Date;

public class Task {
    private String id;
    private String name;
    private String patient;
    private String description;
    private Date deadline;
    private String priority;
    private String mode;
    private String asignTo;

    public Task (String id, String name, String patient, String description, Date deadline, String priority, String mode, String asignTo) {
        this.id = id;
        this.name = name;
        this.patient = patient;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.mode = mode;
        this.asignTo = asignTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getAsignTo() {
        return asignTo;
    }

    public void setAsignTo(String asignTo) {
        this.asignTo = asignTo;
    }

    @NonNull
    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", patient='" + patient + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", priority='" + priority + '\'' +
                ", mode='" + mode + '\'' +
                ", asignTo='" + asignTo + '\'' +
                '}';
    }
}
