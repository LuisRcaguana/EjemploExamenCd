package com.example.ejemploexamencd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ejemploexamencd.RetrofitClient.APICdService;
import com.example.ejemploexamencd.RetrofitClient.RetrofitClient;
import com.example.ejemploexamencd.data.Cd;
import com.example.ejemploexamencd.rvUtiks.CdsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String CLAVE_TITULO = "TITULO";
    RecyclerView rv;
    CdsAdapter cdA;
    LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //para obtener de la url la informacion de los cd
        Retrofit r = RetrofitClient.getClient(APICdService.BASE_URL);
        APICdService api = r.create(APICdService.class);
        Call<ArrayList<Cd>> call = api.obtenerCd();

        //Array cd
        call.enqueue(new Callback<ArrayList<Cd>>() {
            @Override
            public void onResponse(Call<ArrayList<Cd>> call, Response<ArrayList<Cd>> response) {
                if(response.isSuccessful()){
                    ArrayList<Cd> listaCds = response.body();
                    configurarRv(listaCds);

                } else {
                    Log.e("onResponse", "error" + response.code());
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Cd>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "error:" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
                Log.e("onFailure", "error"+ t.getMessage());
            }
        });
    }

    private void configurarRv(final ArrayList<Cd> listaCds) {
        rv = findViewById(R.id.rvCds);

        cdA = new CdsAdapter(listaCds);
        cdA.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrir el siguiente Activity
                Intent i = new Intent(MainActivity.this,
                        CdActivity.class);
                i.putExtra(CLAVE_TITULO,
                        listaCds.get(rv.getChildAdapterPosition(v))
                                .getTitle());
                startActivity(i);
            }
        });
        llm = new LinearLayoutManager(this);

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(cdA);
    }
}
