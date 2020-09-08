package com.gmail.bergrin.whatwouldyouchoose.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gmail.bergrin.whatwouldyouchoose.R;

public class QuestionsActivity extends AppCompatActivity {
    private int level;
    private TextView levelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent getLevel = getIntent();
        level = getLevel.getIntExtra("LEVEL", 0);
        levelTextView = findViewById(R.id.levelTextView);
        levelTextView.setText("LEVEL IS " + level);
    }
}