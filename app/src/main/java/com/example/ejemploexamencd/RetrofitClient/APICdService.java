package com.example.ejemploexamencd.RetrofitClient;

import com.example.ejemploexamencd.data.Cd;
import com.example.ejemploexamencd.data.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APICdService {
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    //DEPENDEN DE LOS DATOS QUE VAMOS BUSCAR

    @GET("cds")
    Call<ArrayList<Cd>> obtenerCd();

    @GET("cds")
    Call<ArrayList<Cd>> obtenerCdPorTitulo(@Query("title") String titulo);

    @GET("coutries/{id_country}/{otra}")
    Call<Country> obtenerPais(@Path("id_country") String pais);



}
