package com.gmail.bergrin.whatwouldyouchoose.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gmail.bergrin.whatwouldyouchoose.R;
import com.gmail.bergrin.whatwouldyouchoose.questions.Question;

public class QuestionsActivity extends AppCompatActivity {
    private int level;
    private TextView levelTextView;
    private TextView questionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent getLevel = getIntent();
        level = getLevel.getIntExtra("LEVEL", 0);
        levelTextView = findViewById(R.id.levelTextView);
        questionTextView = findViewById(R.id.questionTextView);
        levelTextView.setText("LEVEL IS " + level);

        String[] questions = getResources().getStringArray(R.array.easy_questions);
        questionTextView.setText(questions[0]);

    }
}