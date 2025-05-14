package org.example.backendtesina.entities.services;

import jakarta.persistence.Entity;
import org.example.backendtesina.entities.services.ServiceEntity;

@Entity
public class CustomizationEntity extends ServiceEntity {

    private String materialsUsed;
    private String TaskRealized;

    public String getMaterialsUsed() {
        return materialsUsed;
    }

    public void setMaterialsUsed(String materialsUsed) {
        this.materialsUsed = materialsUsed;
    }

    public String getTaskRealized() {
        return TaskRealized;
    }

    public void setTaskRealized(String taskRealized) {
        TaskRealized = taskRealized;
    }
}
