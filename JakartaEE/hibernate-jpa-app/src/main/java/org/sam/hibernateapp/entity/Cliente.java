package org.sam.hibernateapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "clientes_seq_generator")
    @SequenceGenerator(name = "clientes_seq_generator", sequenceName = "clientes_seq", allocationSize = 1)
    private Long id;
    private String nombre;
    private String apellido;

    @Column(name="FORMA_PAGO")
    private String formaPago;

    @Embedded
    private Auditoria auditoria = new Auditoria();


    public Cliente() {
    }

    public Cliente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    @Override
    public String toString() {
        LocalDateTime creado = this.auditoria != null ? auditoria.getCreadoEn(): null;
        LocalDateTime editado = this.auditoria != null ? auditoria.getEditadoEn(): null;

        return "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago + '\''+
                ", creadoEn='" + creado + '\''+
                ", editadoEn='" + editado + '\'';
    }
}