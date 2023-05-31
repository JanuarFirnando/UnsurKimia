package com.if4a.unsurkimia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.if4a.unsurkimia.API.APIRequestData;
import com.if4a.unsurkimia.API.RetroServer;
import com.if4a.unsurkimia.R;
import com.if4a.unsurkimia.model.ModelResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private TextView tv_simbol, tv_nama, tv_massa, tv_nomor, tv_keterangan;
    private String Yid, Ysimbol, Ynama,Ymassa , Ynomor, Yketerangan;
    private Button btn_ubah, btn_hapus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent ambil = getIntent();
        Yid = ambil.getStringExtra("Xid");
        Ysimbol = ambil.getStringExtra("Xsimbol");
        Ynama = ambil.getStringExtra("Xnama");
        Ymassa = ambil.getStringExtra("Xmassa");
        Ynomor = ambil.getStringExtra("Xnomor");
        Yketerangan = ambil.getStringExtra("Xketerangan");

        tv_simbol = findViewById(R.id.tv_simbol);
        tv_nama = findViewById(R.id.tv_nama);
        tv_massa = findViewById(R.id.tv_massa);
        tv_nomor = findViewById(R.id.tv_nomor);
        tv_keterangan = findViewById(R.id.tv_keterangan);
        btn_ubah = findViewById(R.id.btn_ubah);
        btn_hapus = findViewById(R.id.btn_hapus);

        tv_simbol.setText(Ysimbol);
        tv_nama.setText(Ynama);
        tv_massa.setText(Ymassa);
        tv_nomor.setText(Ynomor);
        tv_keterangan.setText(Yketerangan);

        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HapusKuliner();

            }
        });
    }

    private void HapusKuliner()
    {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardDelete(Yid);
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(DetailActivity.this, "Kode: " + kode + " Pesan: " +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}