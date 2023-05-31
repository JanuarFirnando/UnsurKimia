package com.if4a.unsurkimia.activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class TambahActivity extends AppCompatActivity {
    private EditText et_simbol, et_nama, et_massa, et_nomor, et_keterangan;
    private String simbol, nama, massa, nomor, keterangan;
    private Button btn_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        et_simbol = findViewById(R.id.et_simbol);
        et_nama = findViewById(R.id.et_nama);
        et_massa = findViewById(R.id.et_massa);
        et_nomor = findViewById(R.id.et_nomor);
        et_keterangan = findViewById(R.id.et_keterangan);
        btn_tambah = findViewById(R.id.btn_tambah);

        btn_tambah.setOnClickListener(new View.OnClickListener() {
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
                } else {
                    tambahUnsur();
                }
            }
        });
    }



    private void tambahUnsur() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardCreate(simbol, nama, massa, nomor, keterangan);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(TambahActivity.this, "kode : " + kode + " pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}