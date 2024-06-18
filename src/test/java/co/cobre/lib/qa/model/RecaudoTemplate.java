package co.cobre.lib.qa.model;

import java.io.Reader;

public class RecaudoTemplate{
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombrePagador;
    private String celular;
    private String email;
    private String referenciaUna;
    private String referenciaDos;
    private String descripcion;
    private String monto;
    private String fechaVencimiento;
    private String url;

    public RecaudoTemplate(String tipoDocumento, String numeroDocumento, String nombrePagador, String celular, String email, String referenciaUna, String referenciaDos, String descripcion, String monto, String fechaVencimiento, String url) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombrePagador = nombrePagador;
        this.celular = celular;
        this.email = email;
        this.referenciaUna = referenciaUna;
        this.referenciaDos = referenciaDos;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.url = url;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombrePagador() {
        return nombrePagador;
    }

    public void setNombrePagador(String nombrePagador) {
        this.nombrePagador = nombrePagador;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferenciaUna() {
        return referenciaUna;
    }

    public void setReferenciaUna(String referenciaUna) {
        this.referenciaUna = referenciaUna;
    }

    public String getReferenciaDos() {
        return referenciaDos;
    }

    public void setReferenciaDos(String referenciaDos) {
        this.referenciaDos = referenciaDos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RecaudoTemplate{" +
                "tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", nombrePagador='" + nombrePagador + '\'' +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                ", referenciaUna='" + referenciaUna + '\'' +
                ", referenciaDos='" + referenciaDos + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", monto='" + monto + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
