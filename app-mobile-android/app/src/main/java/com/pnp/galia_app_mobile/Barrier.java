package com.pnp.galia_app_mobile;

import androidx.annotation.NonNull;

public class Barrier {
    private String id;
    private String name;
    private String code;
    private String patient;
    private String description;
    private String classification;
    private String priority;
    private String type;

    public Barrier(String id, String name, String code, String patient, String description, String clasification, String priority, String type) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.patient = patient;
        this.description = description;
        this.classification = clasification;
        this.priority = priority;
        this.type = type;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return "Barrier{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", patient='" + patient + '\'' +
                ", description='" + description + '\'' +
                ", clasification=" + classification +
                ", priority='" + priority + '\'' +
                ", mode='" + type + '\'' +
                '}';
    }
}
