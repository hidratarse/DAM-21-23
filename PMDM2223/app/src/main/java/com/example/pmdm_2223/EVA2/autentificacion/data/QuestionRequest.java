package com.example.pmdm_2223.EVA2.autentificacion.data;

public class QuestionRequest {
    String question_text;
    String pub_date;

    public QuestionRequest(String question_text, String pub_date) {
        this.question_text = question_text;
        this.pub_date = pub_date;
    }
}
