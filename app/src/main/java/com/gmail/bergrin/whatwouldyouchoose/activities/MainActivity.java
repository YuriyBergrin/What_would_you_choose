package com.gmail.bergrin.whatwouldyouchoose.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.gmail.bergrin.whatwouldyouchoose.R;
import com.gmail.bergrin.whatwouldyouchoose.utils.MyDialogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit_option) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openChooseLevelActivity(View view) {
        startActivity(new Intent(MainActivity.this, ChooseLevelActivity.class));
    }

    public void openAboutDialog(View view) {
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        FragmentManager manager = getSupportFragmentManager();
        //myDialogFragment.show(manager, "dialog");

        FragmentTransaction transaction = manager.beginTransaction();
        myDialogFragment.show(transaction, "dialog");
    }
}