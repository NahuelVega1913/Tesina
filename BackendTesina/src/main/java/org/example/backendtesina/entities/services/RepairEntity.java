package org.example.backendtesina.entities.services;

import jakarta.persistence.Entity;
import org.example.backendtesina.entities.services.ServiceEntity;

@Entity
public class RepairEntity extends ServiceEntity {
    private String techniclaDiagnosis;
    private String TasksPerformed;
    private String sparesUsed;

    public String getTechniclaDiagnosis() {
        return techniclaDiagnosis;
    }

    public void setTechniclaDiagnosis(String techniclaDiagnosis) {
        this.techniclaDiagnosis = techniclaDiagnosis;
    }

    public String getTasksPerformed() {
        return TasksPerformed;
    }

    public void setTasksPerformed(String tasksPerformed) {
        TasksPerformed = tasksPerformed;
    }

    public String getSparesUsed() {
        return sparesUsed;
    }

    public void setSparesUsed(String sparesUsed) {
        this.sparesUsed = sparesUsed;
    }
}
