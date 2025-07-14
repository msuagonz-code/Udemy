package org.sam.lab.repositorio.Impl;

import org.sam.lab.modelo.Jobs;
import org.sam.lab.repositorio.Repositorio;
import org.sam.lab.util.ConexionBaseDatos;

import javax.print.attribute.standard.JobName;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobsRepositorioImpl implements Repositorio<Jobs, String> {

    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getConnecion();
    }

    @Override
    public List<Jobs> listar() {
        List<Jobs> trabajos = new ArrayList<>();

        try(
                Connection conn = this.getConnection();
                Statement sentencia = conn.createStatement();
                ResultSet resultSet = sentencia.executeQuery("SELECT * FROM JOBS");
                ){

                while(resultSet.next()){
                    Jobs trabajo = crearTrabajo(resultSet);
                    trabajos.add(trabajo);
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trabajos;
    }

    @Override
    public Jobs porId(String id) {
        Jobs trabajo = new Jobs();

        try(
                Connection conn = this.getConnection();
                PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM JOBS WHERE JOB_ID = ?")){
            //En el try no se puede ejecutar el metodo setString porque este metodo no es AutoCloseable
            // prepareStatement de connection si es AutoCloseable
            sentencia.setString(1, id);

            try(ResultSet resultSet = sentencia.executeQuery()) {
                if (resultSet.next()) {
                    trabajo = crearTrabajo(resultSet);
                }
            }
            //Cerramos el resultset aqui porque no pudimos declararlo en el try
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trabajo;
    }

    @Override
    public void guardar(Jobs jobs) {
        String sqlUpdate = "UPDATE JOBS SET JOB_TITLE = ?, MIN_SALARY = ?, MAX_SALARY = ? WHERE JOB_ID = ?";
        try(
                Connection conn = this.getConnection();
                PreparedStatement updateSentencia = conn.prepareStatement(sqlUpdate)){

            updateSentencia.setString(1, jobs.getJobTitle());
            updateSentencia.setInt(2, jobs.getMinSalary());
            updateSentencia.setInt(3, jobs.getMaxSalary());
            updateSentencia.setString(4, jobs.getJobId());

            int filasActualizadas = updateSentencia.executeUpdate();

            // Si no se actualizó ninguna fila, hacemos un INSERT
            if ( filasActualizadas == 0 ) {
                String sqlInsert = "INSERT INTO JOBS (JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY) VALUES (?, ?, ?, ?)";
                try(PreparedStatement insertSentencia = conn.prepareStatement(sqlInsert)){
                    insertSentencia.setString(1, jobs.getJobId());
                    insertSentencia.setString(2, jobs.getJobTitle());
                    insertSentencia.setInt(3, jobs.getMinSalary());
                    insertSentencia.setInt(4, jobs.getMaxSalary());
                    insertSentencia.executeUpdate();
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el trabajo", e);
        }
    }

    @Override
    public void eliminar(String id) {
        String delete = "DELETE FROM JOBS WHERE JOB_ID = ?";
        try(
                Connection conn = this.getConnection();
                PreparedStatement sentenciaEliminar = conn.prepareStatement(delete)){
            sentenciaEliminar.setString(1, id);
            sentenciaEliminar.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el trabajo", e);
        }
    }

    private static Jobs crearTrabajo(ResultSet resultSet) throws SQLException {
        Jobs trabajo = new Jobs();
        trabajo.setJobId(resultSet.getString("JOB_ID"));
        trabajo.setJobTitle(resultSet.getString("JOB_TITLE"));
        trabajo.setMaxSalary(resultSet.getInt("MAX_SALARY"));
        trabajo.setMinSalary(resultSet.getInt("MIN_SALARY"));
        return trabajo;
    }
}
