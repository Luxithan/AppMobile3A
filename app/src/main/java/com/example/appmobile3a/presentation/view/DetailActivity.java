package com.example.appmobile3a.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobile3a.Constants;
import com.example.appmobile3a.R;
import com.example.appmobile3a.Singletons;
import com.example.appmobile3a.presentation.model.Card;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String cardJson = intent.getStringExtra(Constants.KEY_CARD);
        Card card = Singletons.getGson().fromJson(cardJson, Card.class);
        showDetail(card);
    }

    private void showDetail(Card card) {
            txtDetail.setText(card.getHp()+" hp");
    }
}
