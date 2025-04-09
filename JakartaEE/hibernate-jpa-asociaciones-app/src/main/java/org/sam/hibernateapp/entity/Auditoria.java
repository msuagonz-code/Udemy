package org.sam.hibernateapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@Embeddable
public class Auditoria {

    @Column(name="CREADO_EN")
    private LocalDateTime creadoEn;

    @Column(name="EDITADO_EN")
    private LocalDateTime editadoEn;

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    @PrePersist
    public void prePersist(){
        System.out.println("Inicializar algo justo antes del persist");
        this.creadoEn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Inicializar algo justo antes del update");
        this.editadoEn = LocalDateTime.now();
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getEditadoEn() {
        return editadoEn;
    }

    public void setEditadoEn(LocalDateTime editadoEn) {
        this.editadoEn = editadoEn;
    }

}
