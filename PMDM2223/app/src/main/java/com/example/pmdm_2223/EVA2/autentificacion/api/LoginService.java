package com.example.pmdm_2223.EVA2.autentificacion.api;

import com.example.pmdm_2223.EVA2.autentificacion.data.LoginRequest;
import com.example.pmdm_2223.EVA2.autentificacion.data.QuestionRequest;
import com.example.pmdm_2223.EVA2.autentificacion.data.QuestionResponse;
import com.example.pmdm_2223.EVA2.autentificacion.data.TokenResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {
    @POST("polls/api-token-auth/")
    Call<TokenResponse> getToken(@Body LoginRequest loginRequest);

    @GET("polls/api/question/")
    Call<List<QuestionResponse>> getQuestions(@Header("Authorization")String authorization);

    @POST("polls/api/question/")
    Call<QuestionResponse> postQuestion(
            @Header("Authorization")String authorization,
            @Body QuestionRequest questionRequest
    );
}