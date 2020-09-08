package com.gmail.bergrin.whatwouldyouchoose.questions;
import android.util.Log;

public class Question {
    private String question;
    private String questionFirstPart;
    private String questionSecondPart;
    private String divider;

    public Question(String question, String divider) {
        this.question = question;
        this.divider = divider;
        splitQuestion();
    }

    private void splitQuestion() {
        String[] questionParts = question.split(divider);
        questionFirstPart = questionParts[0].trim();
        questionSecondPart = questionParts[1].trim();
    }

    public String getQuestionFirstPart() {
        return questionFirstPart;
    }

    public String getQuestionSecondPart() {
        return questionSecondPart;
    }
}
