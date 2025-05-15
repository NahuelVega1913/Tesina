package org.example.backendtesina.DTOs.Get;

public class GetCustomization extends GetServices {

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
