package com.example.ejemploexamencd.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Catalogo {
    @SerializedName("cds")
    @Expose
    private ArrayList<Cd> cds = null;
    @SerializedName("countries")
    @Expose
    private ArrayList<Country> countries = null;

    public ArrayList<Cd> getCds() {
        return cds;
    }

    public void setCds(ArrayList<Cd> cds) {
        this.cds = cds;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }
}
