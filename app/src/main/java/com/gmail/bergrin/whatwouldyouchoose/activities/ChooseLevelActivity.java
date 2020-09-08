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

import static com.gmail.bergrin.whatwouldyouchoose.utils.Constants.*;

public class ChooseLevelActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        Button easyButton;
        Button normalButton;
        Button hardButton;

        easyButton = findViewById(R.id.easyButton);
        normalButton = findViewById(R.id.normalButton);
        hardButton = findViewById(R.id.hardButton);

        easyButton.setOnClickListener(this);
        normalButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);

//        ActionBar actionBar = this.getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            //NavUtils.navigateUpFromSameTask(this);
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        Intent levelIntent = new Intent(ChooseLevelActivity.this, QuestionsActivity.class);
        switch (v.getId()) {
            case R.id.easyButton:
                levelIntent.putExtra("LEVEL", EASY);
                startActivity(levelIntent);
                break;

            case R.id.normalButton:
                levelIntent.putExtra("LEVEL", NORMAL);
                startActivity(levelIntent);
                break;

            case R.id.hardButton:
                levelIntent.putExtra("LEVEL", HARD);
                startActivity(levelIntent);
                break;
        }
    }
}