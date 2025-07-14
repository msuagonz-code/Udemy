package org.sam.lab.modelo;

import java.util.Date;

public class JobHistory {
    private Integer employeeId;

    private Date startDate;

    private Date endDate;

    private Jobs trabajo;

    private Integer departmentId;

    public JobHistory() {
    }

    public JobHistory(Integer employeeId, Date startDate, Date endDate, Jobs trabajo, Integer departmentId) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trabajo = trabajo;
        this.departmentId = departmentId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Jobs getTrabajo() {
        return trabajo;
    }

    public void setJobs(Jobs trabajo) {
        this.trabajo = trabajo;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Id Empleado: " + this.getEmployeeId() +
                "\t|\t Fecha Inicio: " + this.getStartDate() +
                "\t|\t Fecha Fin: " + this.getEndDate() +
                "\t|\t Id Trabajo: " + this.getTrabajo().getJobId() +
                "\t|\t Id Departamento: " + this.getDepartmentId() + "\t|\t";
    }
}
