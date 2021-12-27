package br.com.alura.leilao.api.retrofit.client;

import retrofit2.Response;

public abstract class WebClient {

    protected  <T> boolean temDados(Response<T> response) {
        return response.isSuccessful() && response.body() != null;
    }

}
