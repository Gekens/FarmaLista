package com.example.giacomo.farmalista;

/**
 * Created by Utente on 19/05/2016.
 */
public class CellaContatti {

    // questi sono gli elementi che rappresentano i campi di ogni elemento della listView
    private String nome;
    private String cognome;
    private String tipoContatto;
    private int id;

    // costruttore

    public CellaContatti(String nome, String cognome, String tipoContatto, int id) {
        this.nome = nome;
        this.cognome = cognome;
        this.tipoContatto = tipoContatto;
        this.id = id;
    }


    // i metodi getter e setter per ogni attributo

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

    public String getTipoContatto() {
        return tipoContatto;
    }

    public void setTipoContatto(String tipoContatto) {
        this.tipoContatto = tipoContatto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
