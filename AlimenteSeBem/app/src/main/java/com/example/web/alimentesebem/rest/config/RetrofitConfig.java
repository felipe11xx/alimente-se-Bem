package com.example.web.alimentesebem.rest.config;

import com.example.web.alimentesebem.rest.RestInterface;
import com.example.web.alimentesebem.rest.commons.AppUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    // atributo
    private final Retrofit retrofit;

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();

    // Nossa configuração será feita no construtor
    public RetrofitConfig() {

        // Precisamos construir um objeto do tipo retrofit
        this.retrofit = new Retrofit.Builder()
                // definimos a url base da nossa aplicação
                .baseUrl(AppUtils.API_BASE_URL)
                // precisamos transformar a nossa resposta que vem em JSON para String
                .addConverterFactory(GsonConverterFactory.create(gson))
                // precisamos de fato criá-lo
                .build();

    }

    // quando você tiver autenticação
    public RetrofitConfig(OkHttpClient client) {
        // Precisamos construir um objeto do tipo retrofit
        this.retrofit = new Retrofit.Builder()
                // definimos a url base da nossa aplicação
                .baseUrl(AppUtils.API_BASE_URL)
                // caso seja necessário colocar um interceptor
                .client(client)
                // precisamos transformar a nossa resposta que vem em JSON para String
                .addConverterFactory(GsonConverterFactory.create())
                // precisamos de fato criá-lo
                .build();
    }

    public RestInterface getRestInterface() {
        return this.retrofit.create(RestInterface.class);
    }

}