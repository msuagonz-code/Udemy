package org.sam.lab.repositorio.Impl;

import org.sam.lab.modelo.JobHistory;
import org.sam.lab.modelo.Jobs;
import org.sam.lab.repositorio.Repositorio;
import org.sam.lab.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JobHistoryRepositoryImp implements Repositorio<JobHistory, Integer> {

    private Connection conn;

    public JobHistoryRepositoryImp() {
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public JobHistoryRepositoryImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<JobHistory> listar() throws SQLException {
        List<JobHistory> trabajos = new ArrayList<>();
        String sql = "SELECT " +
                "    H.EMPLOYEE_ID," +
                "    H.START_DATE," +
                "    H.END_DATE," +
                "    H.JOB_ID," +
                "    H.DEPARTMENT_ID," +
                "    J.JOB_TITLE," +
                "    J.MIN_SALARY," +
                "    J.MAX_SALARY" +
                " FROM JOB_HISTORY H" +
                " INNER JOIN JOBS J ON H.JOB_ID = J.JOB_ID";
        try(
                Statement sentencia = conn.createStatement();
                ResultSet resultSet = sentencia.executeQuery(sql);
        ){

            while(resultSet.next()){
                JobHistory trabajo = crearHistorico(resultSet);
                trabajos.add(trabajo);
            }

        }
        return trabajos;
    }

    @Override
    public JobHistory porId(Integer id) throws SQLException {
        JobHistory historico = new JobHistory();
        String sql = "SELECT " +
                "    H.EMPLOYEE_ID," +
                "    H.START_DATE," +
                "    H.END_DATE," +
                "    H.JOB_ID," +
                "    H.DEPARTMENT_ID," +
                "    J.JOB_TITLE," +
                "    J.MIN_SALARY," +
                "    J.MAX_SALARY" +
                " FROM JOB_HISTORY H" +
                " INNER JOIN JOBS J ON H.JOB_ID = J.JOB_ID WHERE H.EMPLOYEE_ID = ?";

        try(PreparedStatement sentencia = conn.prepareStatement(sql)){
            //En el try no se puede ejecutar el metodo setString porque este metodo no es AutoCloseable
            // prepareStatement de connection si es AutoCloseable
            sentencia.setInt(1, id);

            try(ResultSet resultSet = sentencia.executeQuery()) {
                if (resultSet.next()) {
                    historico = crearHistorico(resultSet);
                }
            }
            //Cerramos el resultset aqui porque no pudimos declararlo en el try
        }
        return historico;
    }

    @Override
    public JobHistory guardar(JobHistory jobHistory) throws SQLException {

        String sqlUpdate = "UPDATE JOB_HISTORY SET EMPLOYEE_ID = ?," +
                " START_DATE = ?," +
                " END_DATE = ?," +
                " JOB_ID = ?," +
                " DEPARTMENT_ID = ?" +
                " WHERE EMPLOYEE_ID = ?";

        try(PreparedStatement updateSentencia = conn.prepareStatement(sqlUpdate)){

            updateSentencia.setInt(1, jobHistory.getEmployeeId());
            updateSentencia.setDate(2, new Date(jobHistory.getStartDate().getTime()));
            updateSentencia.setDate(3, new Date(jobHistory.getEndDate().getTime()));
            updateSentencia.setString(4, jobHistory.getTrabajo().getJobId());
            updateSentencia.setInt(5, jobHistory.getDepartmentId());
            updateSentencia.setInt(6, jobHistory.getEmployeeId());

            int filasActualizadas = updateSentencia.executeUpdate();

            // Si no se actualizó ninguna fila, hacemos un INSERT
            if ( filasActualizadas == 0 ) {
                String sqlInsert = "INSERT INTO JOB_HISTORY (EMPLOYEE_ID, START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID) VALUES (?, ?, ?, ?, ?)";
                try(PreparedStatement insertSentencia = conn.prepareStatement(sqlInsert)){
                    insertSentencia.setInt(1, jobHistory.getEmployeeId());
                    insertSentencia.setDate(2, new Date(jobHistory.getStartDate().getTime()));
                    insertSentencia.setDate(3, new Date(jobHistory.getEndDate().getTime()));
                    insertSentencia.setString(4, jobHistory.getTrabajo().getJobId());
                    insertSentencia.setInt(5, jobHistory.getDepartmentId());
                    insertSentencia.executeUpdate();
                }

            }
            return jobHistory;
        }
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        String delete = "DELETE FROM JOB_HISTORY WHERE EMPLOYEE_ID = ?";
        try(PreparedStatement sentenciaEliminar = conn.prepareStatement(delete)){
            sentenciaEliminar.setInt(1, id);
            sentenciaEliminar.executeUpdate();

        }
    }

    private static JobHistory crearHistorico(ResultSet resultSet) throws SQLException {
        JobHistory historico = new JobHistory();
        historico.setEmployeeId (resultSet.getInt("EMPLOYEE_ID"));
        historico.setStartDate(resultSet.getDate("START_DATE"));
        historico.setEndDate(resultSet.getDate("END_DATE"));
        historico.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));

        Jobs trabajos = new Jobs();
        trabajos.setJobId(resultSet.getString("JOB_ID"));
        trabajos.setJobTitle(resultSet.getString("JOB_TITLE"));
        trabajos.setMaxSalary(resultSet.getInt("MAX_SALARY"));
        trabajos.setMinSalary(resultSet.getInt("MIN_SALARY"));

        historico.setJobs(trabajos);

        return historico;
    }
}