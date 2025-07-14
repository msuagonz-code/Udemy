package org.sam.lab.servicio;

import org.sam.lab.modelo.JobHistory;
import org.sam.lab.modelo.Jobs;

import java.sql.SQLException;
import java.util.List;

public interface Servicio {
    List<JobHistory> listarHistorico() throws SQLException;

    JobHistory porIdHistorico(Integer id) throws SQLException;

    JobHistory guardarHistorico(JobHistory historicoTrabajo) throws SQLException;

    void eliminarHistorico(Integer id) throws SQLException;

    List<Jobs> listarTrabajo() throws SQLException;

    Jobs porIdTrabajo(String Id) throws SQLException;

    Jobs guardarTrabajo(Jobs trabajo) throws SQLException;

    void eliminarTrabajo(String id) throws SQLException;

    JobHistory guardarHistoricoConTrabajo(JobHistory historicoTrabajo, Jobs trabajo) throws SQLException;
}
