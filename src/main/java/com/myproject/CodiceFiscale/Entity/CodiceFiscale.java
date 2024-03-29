package com.myproject.CodiceFiscale.Entity;


public class CodiceFiscale {

    String nome;
    String cognome;
    String data;

    String gender;
    String comune;

    @Override
    public String toString() {
        return "CodiceFiscale{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data='" + data + '\'' +
                ", gender='" + gender + '\'' +
                ", comune='" + comune + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }
}