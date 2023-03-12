package com.example.pmdm_2223.EVA2.autentificacion.api;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.pmdm_2223.EVA2.autentificacion.data.LoginRequest;
import com.example.pmdm_2223.EVA2.autentificacion.data.QuestionRequest;
import com.example.pmdm_2223.EVA2.autentificacion.data.QuestionResponse;
import com.example.pmdm_2223.EVA2.autentificacion.data.TokenResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepository {
    //200 profe
    private static final String BASE_URL = "http://192.168.20.200:8000/";
    private LoginService loginService;
    private MutableLiveData<TokenResponse> tokenLiveData;
    private MutableLiveData<List<QuestionResponse>> questionListLiveData;
    public LoginRepository(){
        tokenLiveData = new MutableLiveData<>();

        questionListLiveData = new MutableLiveData<>();

        loginService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginService.class);
    }

    public void getToken(LoginRequest loginRequest){
        loginService.getToken(loginRequest)
                .enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        if (response.body() != null) {
                            Log.d(TAG,"data encontradp");
                            tokenLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        Log.d(TAG,"no encontrado ");
                        tokenLiveData.postValue(null);
                    }
                });
    }

    public void getQuestions(String token){
        Log.d(TAG,"USANDO"+token);
        loginService.getQuestions("Token "+token)
                .enqueue(new Callback<List<QuestionResponse>>() {
                    @Override
                    public void onResponse(Call<List<QuestionResponse>> call, Response<List<QuestionResponse>> response) {
                        if (response.body() != null) {
                            Log.d(TAG,"PREGUNTAS LLAMADAS");
                            List<QuestionResponse> questionList = response.body();
                            questionListLiveData.postValue(questionList);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<QuestionResponse>> call, Throwable t) {
                        Log.d(TAG,"PREGUNTAS NO LLAMADAS");
                    }
                });
    }
    public void postQuestion(String authorization, QuestionRequest questionRequest){
        loginService.postQuestion("Token "+authorization, questionRequest)
                .enqueue(new Callback<QuestionResponse>() {
                    @Override
                    public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                        if (response.body() != null) {
                            Log.d(TAG, response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionResponse> call, Throwable t) {
                        Log.d(TAG, "No se subió nada");
                    }
                });
    }

    public MutableLiveData<TokenResponse> getTokenLiveData() {
        return tokenLiveData;
    }

    public MutableLiveData<List<QuestionResponse>> getQuestionListLiveData() {
        return questionListLiveData;
    }
}
