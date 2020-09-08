package com.gmail.bergrin.whatwouldyouchoose.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gmail.bergrin.whatwouldyouchoose.R;
import com.gmail.bergrin.whatwouldyouchoose.questions.Question;

import java.util.ArrayList;

import static com.gmail.bergrin.whatwouldyouchoose.utils.Constants.*;

public class QuestionsActivity extends AppCompatActivity {
    private int level;
    private String[] questionsFromResources;
    private ArrayList<Question> questions;
    private String divider;
    private Button startStopButton;
    private Button getResultButton;
    private boolean isGameStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        startStopButton = findViewById(R.id.startStopButton);
        getResultButton = findViewById(R.id.getResultButton);

        questions = new ArrayList<>();
        divider = getResources().getString(R.string.or);

        Intent getLevel = getIntent();
        level = getLevel.getIntExtra("LEVEL", 0);
        fillQuestionList(level);
        setQuestionsObjectList();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

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

    public void startStopGame(View view) {
        if (!isGameStarted) {
            startStopButton.setText(getResources().getText(R.string.stop));
            getResultButton.setEnabled(false);
            isGameStarted = true;
        } else {
            startStopButton.setText(getResources().getText(R.string.start));
            getResultButton.setEnabled(true);
            isGameStarted = false;
        }
    }
}