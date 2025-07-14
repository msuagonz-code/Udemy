package org.sam.lab.servicio;

import org.sam.lab.modelo.JobHistory;
import org.sam.lab.modelo.Jobs;
import org.sam.lab.repositorio.Impl.JobHistoryRepositoryImp;
import org.sam.lab.repositorio.Impl.JobsRepositorioImpl;
import org.sam.lab.repositorio.Repositorio;
import org.sam.lab.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RRHHServicio implements Servicio{

    Repositorio<JobHistory, Integer> historicoRepositorio;
    Repositorio<Jobs, String> trabajosRepositorio;

    public RRHHServicio() {
        this.historicoRepositorio = new JobHistoryRepositoryImp();
        this.trabajosRepositorio = new JobsRepositorioImpl();
    }

    @Override
    public List<JobHistory> listarHistorico() throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnecion()){
            historicoRepositorio.setConn(conn);
            return historicoRepositorio.listar();
        }
    }

    @Override
    public JobHistory porIdHistorico(Integer id) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnecion()){
            historicoRepositorio.setConn(conn);
            return historicoRepositorio.porId(id);
        }
    }

    @Override
    public JobHistory guardarHistorico(JobHistory historicoTrabajo) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnecion()){
            historicoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try{
                historicoRepositorio.guardar(historicoTrabajo);
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
            return historicoRepositorio.guardar(historicoTrabajo);
        }
    }

    @Override
    public void eliminarHistorico(Integer id) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnecion()){
            historicoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try{
                historicoRepositorio.eliminar(id);
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Jobs> listarTrabajo() throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnecion()){
            trabajosRepositorio.setConn(conn);
            return trabajosRepositorio.listar();
        }
    }

    @Override
    public Jobs porIdTrabajo(String id) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnecion()){
            trabajosRepositorio.setConn(conn);
            return trabajosRepositorio.porId(id);
        }
    }

    @Override
    public Jobs guardarTrabajo(Jobs trabajo) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnecion()){
            trabajosRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try{
                trabajosRepositorio.guardar(trabajo);
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
            return trabajosRepositorio.guardar(trabajo);
        }
    }

    @Override
    public void eliminarTrabajo(String id) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnecion()){
            trabajosRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try{
                trabajosRepositorio.eliminar(id);
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public JobHistory guardarHistoricoConTrabajo(JobHistory historicoTrabajo, Jobs trabajo) throws SQLException {
        try(Connection conn = ConexionBaseDatos.getConnecion()){
            trabajosRepositorio.setConn(conn);
            historicoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            JobHistory nuevoHistorico = new JobHistory();
            try{
                trabajosRepositorio.guardar(trabajo);

                historicoTrabajo.setJobs(trabajo);
                nuevoHistorico = historicoRepositorio.guardar(historicoTrabajo);

                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
            return nuevoHistorico;
        }
    }
}
