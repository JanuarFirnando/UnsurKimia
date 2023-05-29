package com.if4a.unsurkimia.model;

import java.util.List;

public class ModelResponse {
    private String kode, pesan;
    private List<ModelUnsur> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelUnsur> getData() {
        return data;
    }
}
