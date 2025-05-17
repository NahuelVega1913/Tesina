package org.example.backendtesina.DTOs.Get;

public class GetCustomization extends GetServices {

    private String materialsUsed;
    private String taskRealized;

    public String getMaterialsUsed() {
        return materialsUsed;
    }

    public void setMaterialsUsed(String materialsUsed) {
        this.materialsUsed = materialsUsed;
    }


    public String getTaskRealized() {
        return taskRealized;
    }

    public void setTaskRealized(String taskRealized) {
        this.taskRealized = taskRealized;
    }
}
