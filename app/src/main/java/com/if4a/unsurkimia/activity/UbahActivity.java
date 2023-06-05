package com.if4a.unsurkimia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.if4a.unsurkimia.API.APIRequestData;
import com.if4a.unsurkimia.API.RetroServer;
import com.if4a.unsurkimia.R;
import com.if4a.unsurkimia.model.ModelResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private String Zid,Zsimbol, Znama, Zmassa, Znomor, Zketerangan;
    private String  simbol, nama, massa, nomor, keterangan;
    private EditText et_simbol, et_nama, et_massa, et_nomor, et_keterangan;
    private Button btn_ubah, btn_kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);
        et_simbol = findViewById(R.id.et_simbol);
        et_nama = findViewById(R.id.et_nama);
        et_massa = findViewById(R.id.et_massa);
        et_nomor = findViewById(R.id.et_nomor);
        et_keterangan = findViewById(R.id.et_keterangan);
        btn_ubah = findViewById(R.id.btn_ubah);
        btn_kembali = findViewById(R.id.btn_kembali);

        Intent ambil = getIntent();
        Zid = ambil.getStringExtra("Yid");
        Zsimbol = ambil.getStringExtra("Ysimbol");
        Znama = ambil.getStringExtra("Ynama");
        Zmassa = ambil.getStringExtra("Ymassa");
        Znomor = ambil.getStringExtra("Ynomor");
        Zketerangan = ambil.getStringExtra("Yketerangan");

        et_simbol.setText(Zsimbol);
        et_nama.setText(Znama);
        et_massa.setText(Zmassa);
        et_nomor.setText(Znomor);
        et_keterangan.setText(Zketerangan);

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simbol = et_simbol.getText().toString();
                nama = et_nama.getText().toString();
                massa = et_massa.getText().toString();
                nomor = et_nomor.getText().toString();
                keterangan = et_keterangan.getText().toString();

                if (simbol.trim().equals("")) {
                    et_simbol.setError("Simbol Harus Diisi");
                }
                if (nama.trim().equals("")) {
                    et_nama.setError("Nama Harus Diisi");
                }
                if (massa.trim().equals("")) {
                    et_massa.setError("Massa Harus Diisi");
                }
                if (nomor.trim().equals("")) {
                    et_nomor.setError("Nomor Harus Diisi");
                }
                if (keterangan.trim().equals("")) {
                    et_keterangan.setError("Keterangan Harus Diisi");
                }
                else {
                    ubahKuliner();
                }
            }
        });
        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UbahActivity.this, MainActivity.class));
            }
        });
    }
    private void ubahKuliner(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardUpdate(Zid, simbol, nama, massa, nomor, keterangan);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(UbahActivity.this, "Kode : " + kode + ", Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}