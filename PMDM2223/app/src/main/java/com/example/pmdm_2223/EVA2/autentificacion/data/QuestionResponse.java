package com.example.pmdm_2223.EVA2.autentificacion.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionResponse {
    @SerializedName("question_text")
    @Expose
    private String questionText;

    public String getQuestionText() {
        return questionText;
    }
}
