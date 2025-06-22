/*
    Nombre del archivo: WinningActivity.java
    Nombre del proyecto: Green Carson Reecicla! Game

    Creado y Desarrollado por:

    César Guerra Martínez
    Alejandro Daniel Moctezuma Cruz

    En colaboración con el Instituto Tecnológico y
    de Estudios Superiores de Monterrey y la empresa Green Carson.
*/

package greencarson.videojuego;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import java.util.Objects;
import java.util.Random;

public class WinningActivity extends Activity {

    int winningState, levelNumber;
    boolean isSecondState = false;
    MediaPlayer effectLoseWah, effectWinWoo;

    // Create Results View
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get data
        winningState = Objects.requireNonNull(getIntent().getExtras()).getInt("winningState");
        levelNumber = Objects.requireNonNull(getIntent().getExtras()).getInt("levelNumber");
        isSecondState = getIntent().getExtras().getBoolean("isSecondState");

        effectLoseWah = MediaPlayer.create(this, R.raw.effect_losing);
        effectWinWoo = MediaPlayer.create(this, R.raw.effect_winning);

        // Choose content based on winning state
        // If won
        if (winningState == 1 && !isSecondState) {
            effectWinWoo.start();
            setContentView(R.layout.activity_winning);
        }
        else if (winningState == 1){
            setContentView(R.layout.activity_winning2);

            // Select random tip
            AppCompatButton buttonTip = findViewById(R.id.stateDescription);
            Random randomComponent = new Random();

            // Use XML to sort tips
            String[] winningTips = getResources().getStringArray(R.array.winning_tips);
            int index = randomComponent.nextInt(10);
            String tip = winningTips[index];
            buttonTip.setText(tip);
        }

        // If lost
        else if (winningState == 0 && !isSecondState) {
            effectLoseWah.start();
            setContentView(R.layout.activity_losing);
        }
        else if (winningState == 0) {
            setContentView(R.layout.activity_losing2);
        }
    }

    // Go to levels
    public void goToLevels(View v){
        Intent intent = new Intent(this, SelectLevelActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToSecondPart(View v){
        Intent intent = new Intent(this, WinningActivity.class);
        intent.putExtra("isSecondState", true);
        intent.putExtra("levelNumber", levelNumber);
        intent.putExtra("winningState", winningState);
        startActivity(intent);
        finish();
    }

    public void playLevelAgain(View v){
        GameView gameView = new GameView(this, levelNumber);
        setContentView(gameView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (effectLoseWah != null) {
            effectLoseWah.stop();
            effectLoseWah.release();
            effectLoseWah = null;
        }
        if (effectWinWoo != null) {
            effectWinWoo.stop();
            effectWinWoo.release();
            effectWinWoo = null;
        }
    }
    
    // Override return
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
    }
}