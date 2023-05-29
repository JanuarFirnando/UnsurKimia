package com.if4a.unsurkimia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.if4a.unsurkimia.API.APIRequestData;
import com.if4a.unsurkimia.API.RetroServer;
import com.if4a.unsurkimia.R;
import com.if4a.unsurkimia.adapter.AdapterUnsur;
import com.if4a.unsurkimia.model.ModelResponse;
import com.if4a.unsurkimia.model.ModelUnsur;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvUnsur;
    private FloatingActionButton fabtambah;
    private ProgressBar pbUnsur;
    private RecyclerView.Adapter adUnsur;
    private RecyclerView.LayoutManager lmUnsur;
    private List<ModelUnsur> listkuliner = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvUnsur = findViewById(R.id.rv_unsur);
        fabtambah = findViewById(R.id.fab_tambah);
        pbUnsur = findViewById(R.id.pb_unsur);

        lmUnsur = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvUnsur.setLayoutManager(lmUnsur);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RetrieveUnsur();
    }
    public void RetrieveUnsur(){
        pbUnsur.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardRetrieve();
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listkuliner = response.body().getData();

                adUnsur = new AdapterUnsur(MainActivity.this, listkuliner);
                rvUnsur.setAdapter(adUnsur);
                adUnsur.notifyDataSetChanged();

                pbUnsur.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                pbUnsur.setVisibility(View.GONE);
            }
        });


    }
}