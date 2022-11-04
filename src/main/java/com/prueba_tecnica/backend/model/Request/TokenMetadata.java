package com.prueba_tecnica.backend.model.Request;

public class TokenMetadata {
    private String user;
    private Long rol_id;
    private String rol_name;
    private Boolean read;
    private Boolean edit;
    private Boolean delete;
    private Boolean active;

    public TokenMetadata() {

    }

    public TokenMetadata(
             String user,
             Long rol_id,
             String rol_name,
             Boolean read,
             Boolean edit,
             Boolean delete,
             Boolean active
    ){
        this.user = user;
        this.rol_id = rol_id;
        this.rol_name = rol_name;
        this.read = read;
        this.edit = edit;
        this.delete = delete;
        this.active = active;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getRol_id() {
        return rol_id;
    }

    public void setRol_id(Long rol_id) {
        this.rol_id = rol_id;
    }

    public String getRol_name() {
        return rol_name;
    }

    public void setRol_name(String rol_name) {
        this.rol_name = rol_name;
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

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
