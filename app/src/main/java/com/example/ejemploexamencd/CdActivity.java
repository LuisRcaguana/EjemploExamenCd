package com.example.ejemploexamencd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejemploexamencd.RetrofitClient.APICdService;
import com.example.ejemploexamencd.RetrofitClient.RetrofitClient;
import com.example.ejemploexamencd.data.Cd;
import com.example.ejemploexamencd.data.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CdActivity extends AppCompatActivity {

    TextView tvTit;
    TextView tvAut;
    TextView tvCom;
    TextView tvAnio;
    TextView tvPrec;
    ImageView ivBand;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd);

       tvTit = findViewById(R.id.tvTituloCd);
       tvAut = findViewById(R.id.tvAutorCd);
       tvCom = findViewById(R.id.tvCompania);
       tvAnio = findViewById(R.id.tvAnio);
       tvPrec = findViewById(R.id.tvPrecio);
       ivBand = findViewById(R.id.ivBandera);

       String titulo = getIntent().getStringExtra(MainActivity.CLAVE_TITULO);
        Retrofit r = RetrofitClient.getClient(APICdService.BASE_URL);
        APICdService api = r.create(APICdService.class);
        Call<ArrayList<Cd>> call = api.obtenerCdPorTitulo(titulo);

        call.enqueue(new Callback<ArrayList<Cd>>() {
            @Override
            public void onResponse(Call<ArrayList<Cd>> call, Response<ArrayList<Cd>> response) {
               if(response.isSuccessful()){
                   ArrayList<Cd> cds = response.body();
                   Cd cd = cds.get(0);

                   tvTit.setText(cd.getTitle());
                   tvAut.setText(cd.getArtist());
                   tvCom.setText(cd.getCompany());
                   tvAnio.setText(cd.getYear());
                   tvPrec.setText(cd.getPrice());
                   String pais = cd.getCountry();

                   obtenerPais(pais);


               }
            }

            @Override
            public void onFailure(Call<ArrayList<Cd>> call, Throwable t) {

            }
        });
    }

    private void obtenerPais(String pais){
        Retrofit r = RetrofitClient.getClient(APICdService.BASE_URL);
        APICdService api = r.create(APICdService.class);
        Call<Country> call = api.obtenerPais(pais);

        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                if (response.isSuccessful()) {
                    Country country = response.body();
                    String flag = country.getFlag();
                    //cargar Imagegnes el imageView
                    switch (flag) {
                        case "australia":
                            ivBand.setImageResource(R.drawable.australia);
                            break;
                        case "denmark":
                            ivBand.setImageResource(R.drawable.denmark);
                            break;
                        case "italy":
                            ivBand.setImageResource(R.drawable.italy);
                            break;
                        case "spain":
                            ivBand.setImageResource(R.drawable.spain);
                            break;
                        case "uk":
                            ivBand.setImageResource(R.drawable.uk);
                            break;
                        case "usa":
                            ivBand.setImageResource(R.drawable.usa);
                            break;

                    }
                } else {
                    Log.e("onResponse", "obtenerPais: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.e("onFailure", "obtenerPais: " + t.getMessage());

            }
        });

    }
}
