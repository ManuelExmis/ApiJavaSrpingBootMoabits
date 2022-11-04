package com.prueba_tecnica.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(name = "rol")
@Entity(name = "rol")
@Data
public class Rol {
    @Id
    @SequenceGenerator(
            name = "rols_sequence",
            sequenceName = "rols_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rols_sequence"
    )
    @Column(name = "id", updatable = false, nullable = true)
    private Long id;

    @NotBlank(message = "You most enter a name cuz is mandatory.")
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "read")
    private Boolean read;

    @Column(name = "edit")
    private Boolean edit;

    @Column(name = "delete")
    private Boolean delete;

    @Column(name = "active")
    private Boolean active;

    public Rol() {

    }

    public  Rol(
            String name,
            Boolean read,
            Boolean edit,
            Boolean delete,
            Boolean active
    ) {
        this.id = id;
        this.name = name;
        this.read = read;
        this.edit = edit;
        this.delete = delete;
        this.active = active;
    }

    public  Rol(
            Long id,
            String name,
            Boolean read,
            Boolean edit,
            Boolean delete,
            Boolean active
    ) {
        this.name = name;
        this.read = read;
        this.edit = edit;
        this.delete = delete;
        this.active = active;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Boolean getActive() {
        return active;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

