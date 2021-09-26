package com.example.badanieliczbynaturalnej;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private boolean isPrimeNum(int num) {
        for(int i = 2; i <= (int)Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkNumber(View view) {
        EditText numberObj = findViewById(R.id.number);
        TextView scoreObj = findViewById(R.id.score);

        try {
            if(numberObj.getText().toString().trim().charAt(0) == '0') {
                throw new Exception("Twoja liczba nie może zaczynać się od 0!");
            }

            int number = Integer.parseInt(numberObj.getText().toString().trim());

            if(number <= 0) {
                throw new Exception("Twoja liczba musi być większa niż 0!");
            }

            boolean czyPierwsza = true;
            boolean czyParzysta = true;
            boolean czyDoskonala = false;
            boolean czyPalindromiczna = false;
            boolean czySfeniczna = false;

            // ================== 1 ==================

            czyPierwsza = isPrimeNum(number);

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

            String tempOriginal = numberObj.getText().toString().trim(), tempReversed = "";

            for(int i = tempOriginal.length()-1; i >= 0; i--) {
                tempReversed += tempOriginal.charAt(i);
            }

            if(tempOriginal.equals(tempReversed)) {
                czyPalindromiczna = true;
            }

            // ================== 5 ==================

            Vector<Integer> primeDivisors = new Vector();

            if(number%2 == 0) {
                primeDivisors.add(2);
            }

            for(int i = 3; i <= (int)Math.sqrt(number); i += 2) {
                if(isPrimeNum(i)) {
                    if(number % i == 0) {
                        primeDivisors.add(i);
                    }
                }
            }

            if(primeDivisors.size() == 3) {
                czySfeniczna = true;
            }
            /*else if (primeDivisors.size() > 3) {
                for(int i = 0; i <= primeDivisors.size()-3; i++) {
                    for(int j = i+1; j <= primeDivisors.size()-2; j++) {
                        for(int k = j+1; k <= primeDivisors.size()-1; k++) {
                            if(number == primeDivisors.elementAt(i)*primeDivisors.elementAt(j)*primeDivisors.elementAt(k)) {
                                czySfeniczna = true;
                                break;
                            }
                        }
                        if(czySfeniczna) {
                            break;
                        }
                    }
                    if(czySfeniczna) {
                        break;
                    }
                }
            }*/

            // =======================================

            String score = "Twoja liczba jest:\n";

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

            if(czyPalindromiczna) {
                score += "- palindromiczna\n";
            }
            else {
                score += "- niepalindromiczna\n";
            }

            if(czySfeniczna) {
                score += "- sfeniczna\n";
            }
            else {
                score += "- niesfeniczna\n";
            }

            scoreObj.setText(score);

        } catch (NumberFormatException e) {
            scoreObj.setText("To nie jest liczba!");
        } catch (Exception e) {
            scoreObj.setText(e.getMessage());
        }
    }
}