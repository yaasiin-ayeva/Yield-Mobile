package com.ifnti.yield.controller;

import com.ifnti.yield.model.Result;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL = "http://192.168.60.108:8000/contacts/";
    @GET("cjson")
    Call<List<Result>> getContacts();
}

