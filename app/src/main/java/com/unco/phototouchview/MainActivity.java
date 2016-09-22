package com.unco.phototouchview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton mMuchPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMuchPhoto = (AppCompatButton) findViewById(R.id.btn_much_photo);
        mMuchPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_much_photo:
                startActivity(new Intent(this,PhotoTouchActivity.class));
                break;
        }
    }
}
