package org.example.backendtesina.DTOs.Get;

public class GetRepair extends GetServices {

    private String techniclaDiagnosis;
    private String tasksPerformed;
    private String sparesUsed;

    public String getTechniclaDiagnosis() {
        return techniclaDiagnosis;
    }

    public void setTechniclaDiagnosis(String techniclaDiagnosis) {
        this.techniclaDiagnosis = techniclaDiagnosis;
    }



    public String getSparesUsed() {
        return sparesUsed;
    }

    public void setSparesUsed(String sparesUsed) {
        this.sparesUsed = sparesUsed;
    }

    public String getTasksPerformed() {
        return tasksPerformed;
    }

    public void setTasksPerformed(String tasksPerformed) {
        this.tasksPerformed = tasksPerformed;
    }
}
