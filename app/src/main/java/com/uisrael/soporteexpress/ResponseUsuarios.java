package com.uisrael.soporteexpress;

public class ResponseUsuarios {

    private String codPersona;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String codRol;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseUsuarios() {
    }

    /**
     *
     * @param apellidos
     * @param codPersona
     * @param telefono
     * @param codRol
     * @param email
     * @param nombres
     */
    public ResponseUsuarios(String codPersona, String nombres, String apellidos, String email, String telefono, String codRol) {
        super();
        this.codPersona = codPersona;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.codRol = codRol;
    }

    public String getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(String codPersona) {
        this.codPersona = codPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodRol() {
        return codRol;
    }

    public void setCodRol(String codRol) {
        this.codRol = codRol;
    }

}
