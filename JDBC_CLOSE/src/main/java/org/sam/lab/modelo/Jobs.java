package org.sam.lab.modelo;

public class Jobs {
    private String jobId;

    private String jobTitle;

    private Integer minSalary;

    private Integer maxSalary;

    public Jobs() {
    }

    public Jobs(String jobId, String jobTitle, Integer minSalary, Integer maxSalary) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public String toString() {
        return "Id: " + this.getJobId() +
                "\t|\t Nombre: " + this.getJobTitle() +
                "\t|\t Salario maximo: "+ this.getMaxSalary() +
                "\t|\t Salario minimo: "+ this.getMinSalary() + "\t|\t";
    }

}
