package com.example.badanieliczbynaturalnej;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkNumber(View view) {
        EditText numberObj = (EditText) findViewById(R.id.number);
        TextView scoreObj = (TextView) findViewById(R.id.score);

        try {
            int number = Integer.parseInt(numberObj.getText().toString());

            if(number <= 0) {
                throw new Exception("Twoja liczba musi być większa niż 0!");
            }

            boolean czyPierwsza = true;
            boolean czyParzysta = true;
            boolean czyDoskonala = false;
            boolean czyPalindromiczna = true;
            boolean czySfeniczna = true;

            // ================== 1 ==================

            for(int i = 2; i <= (int)Math.sqrt(number); i++) {
                if(number % i == 0) {
                    czyPierwsza = false;
                    break;
                }
            }

            // ================== 2 ==================

            if(number % 2 == 1) {
                czyParzysta = false;
            }

            // ================== 3 ==================

            int sumaDzielnikow = 1;

            for(int i = 2; i <= number / 2; i++) {
                if(number % i == 0) {
                    sumaDzielnikow += i;
                }
            }

            if(sumaDzielnikow == number) {
                czyDoskonala = true;
            }

            // ================== 4 ==================



            // =======================================

            String score = "Twoja liczba:\n";

            if(number == 1) {
                score += "- ani pierwsza, ani złożona\n";
            }
            else if(czyPierwsza) {
                score += "- pierwsza\n";
            }
            else {
                score += "- złożona\n";
            }

            if(czyParzysta) {
                score += "- parzysta\n";
            }
            else {
                score += "- nieparzysta\n";
            }

            if(czyDoskonala && number != 1) {
                score += "- doskonała\n";
            }
            else {
                score += "- niedoskonała\n";
            }

            scoreObj.setText(score);

        } catch (NumberFormatException e) {
            scoreObj.setText("To nie jest liczba!");
        } catch (Exception e) {
            scoreObj.setText(e.getMessage());
        }
    }
}