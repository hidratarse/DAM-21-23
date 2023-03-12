package com.example.pmdm_2223.EVA2.autentificacion;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pmdm_2223.EVA2.autentificacion.api.LoginRepository;
import com.example.pmdm_2223.EVA2.autentificacion.data.LoginRequest;
import com.example.pmdm_2223.EVA2.autentificacion.data.QuestionRequest;
import com.example.pmdm_2223.EVA2.autentificacion.data.QuestionResponse;
import com.example.pmdm_2223.EVA2.autentificacion.data.TokenResponse;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }
    private LoginRepository loginRepository;
    private LiveData<TokenResponse> tokenLiveData;
    private LiveData<List<QuestionResponse>> questionListLiveData;
    public void init(){
        loginRepository = new LoginRepository();
        tokenLiveData = loginRepository.getTokenLiveData();
        questionListLiveData = loginRepository.getQuestionListLiveData();
    }

    public void getToken(LoginRequest loginRequest){
        loginRepository.getToken(loginRequest);
    }

    public void getQuestions(String token){
        loginRepository.getQuestions(token);
    }
    public void postQuestion(String token, QuestionRequest questionRequest){
        loginRepository.postQuestion(token, questionRequest);
    }

    public LiveData<TokenResponse> getTokenLiveData() {
        return tokenLiveData;
    }

    public LiveData<List<QuestionResponse>> getQuestionListLiveData() {
        return questionListLiveData;
    }
}
