package com.gmail.bergrin.whatwouldyouchoose.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gmail.bergrin.whatwouldyouchoose.R;
import com.gmail.bergrin.whatwouldyouchoose.questions.Question;

import java.util.ArrayList;
import java.util.Collections;

import static com.gmail.bergrin.whatwouldyouchoose.utils.Constants.*;

public class QuestionsActivity extends AppCompatActivity {
    private int level;
    private String[] questionsFromResources;
    private ArrayList<Question> questions;
    private String divider;
    private Button firstQuestionButton;
    private Button secondQuestionButton;
    private Button stopAndShowResultButton;
    private int questionCounter = 0;// счетчик вопросов (всего)
    private int firstQuestionCounter = 0;// счетчик вопросов 1 категории
    private int secondQuestionCounter = 0;// счетчик вопросов 2 категории

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

//        ActionBar actionBar = this.getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        firstQuestionButton = findViewById(R.id.firstQuestionButton);
        secondQuestionButton = findViewById(R.id.secondQuestionButton);
        stopAndShowResultButton = findViewById(R.id.stopAndShowResultButton);

        questions = new ArrayList<>();
        divider = getResources().getString(R.string.or);

        Intent getLevel = getIntent();
        level = getLevel.getIntExtra("LEVEL", 0);
        fillQuestionList(level);
        setQuestionsObjectList();
        Collections.shuffle(questions);
        setQuestionsFields();
    }

    private void setQuestionsFields() {
        Log.d("GAME", "вопрос номер " + 0);
        firstQuestionButton.setText(questions.get(0).getQuestionFirstPart());
        secondQuestionButton.setText(questions.get(0).getQuestionSecondPart());
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//           // NavUtils.navigateUpFromSameTask(this);
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void fillQuestionList(int level) {
        switch (level) {
            case EASY:
                questionsFromResources = getResources().getStringArray(R.array.easy_questions);
                break;

            case NORMAL:
                questionsFromResources = getResources().getStringArray(R.array.normal_questions);
                break;

            case HARD:
                questionsFromResources = getResources().getStringArray(R.array.hard_questions);
                break;
        }
    }

    private void setQuestionsObjectList() {
        for (String questionText : questionsFromResources) {
            questions.add(new Question(questionText, divider));
        }
    }

    public void firstChose(View view) {
        showNextQuestion();
        firstQuestionCounter++;
    }

    public void secondChose(View view) {
        showNextQuestion();
        secondQuestionCounter++;
    }

    private void showNextQuestion() {
        stopAndShowResultButton.setVisibility(View.VISIBLE);
        stopAndShowResultButton.setEnabled(true);
        questionCounter++;
        if (questionCounter < questions.size()) {
            Log.d("GAME", "вопрос номер " + questionCounter);
            firstQuestionButton.setText(questions.get(questionCounter).getQuestionFirstPart());
            secondQuestionButton.setText(questions.get(questionCounter).getQuestionSecondPart());
        } else {
            Log.d("GAME", "вопросы кончились");
            showResult();
            dropData();
        }
    }

    public void showResult() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.result_layout, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(QuestionsActivity.this);
        alertDialogBuilderUserInput.setView(view);
        alertDialogBuilderUserInput.setPositiveButton(getResources().getText(R.string.restart), null);

        TextView resultLabelTextView = view.findViewById(R.id.resultLabelTextView);
        TextView resultDescriptionTextView = view.findViewById(R.id.resultDescriptionTextView);

        resultLabelTextView.setText(setResultAlert());

        AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();
    }

    public void showResultClick(View view) {
        showResult();
        dropData();
    }

    private String setResultAlert() {
        String result;
        if (firstQuestionCounter > secondQuestionCounter) {
            result = getResources().getString(R.string.pervert);
            if (secondQuestionCounter > 0 && firstQuestionCounter % secondQuestionCounter > 0) {
                result = getResources().getString(R.string.cool_dog);
            }
        } else {
            result = getResources().getString(R.string.lunatic);
            if (firstQuestionCounter > 0 && secondQuestionCounter % firstQuestionCounter > 0) {
                result = getResources().getString(R.string.crazy);
            }
        }

        if (firstQuestionCounter == secondQuestionCounter) {
            result = getResources().getString(R.string.normal_dude);
        }
        return getResources().getString(R.string.you) + " " + result;
    }

    private void dropData() {
        questionCounter = 0;
        firstQuestionCounter = 0;
        secondQuestionCounter = 0;
        Collections.shuffle(questions);
        firstQuestionButton.setText(questions.get(0).getQuestionFirstPart());
        secondQuestionButton.setText(questions.get(0).getQuestionSecondPart());
    }
}