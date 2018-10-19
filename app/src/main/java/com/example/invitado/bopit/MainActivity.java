package com.example.invitado.bopit;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    Button botonJugar;
    ImageView ivBopit;

    boolean flag, derecha, izquierda, adelante, atras, bopit;
    int random, x, y, z;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensor = (SensorManager) getSystemService(SENSOR_SERVICE);

        botonJugar = (Button) findViewById(R.id.botonJugar);
        ivBopit = (ImageView) findViewById(R.id.ivBopit);

        sensor.registerListener(this, sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);


        botonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inicio(true);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (derecha == true) {
            if (event.values[0] <= -12) {
                //Acierto();
                //Mensaje("Muy Bien");
                derecha = false;
                Inicio(true);
            }
        }
        if (izquierda == true) {
            if (event.values[0] >= 12) {
                //Acierto();
                //Mensaje("Muy Bien");
                izquierda = false;
                Inicio(true);
            }
        }
        if (atras == true) {
            if (event.values[2] > 7) {
                //Acierto();
                //Mensaje("Muy Bien");
                atras = false;
                Inicio(true);
            }
        }
        if (adelante == true) {
            // Wait(1000);
            if (event.values[2] < -7) {
                //Acierto();
                //Mensaje("Muy Bien");
                adelante = false;
                Inicio(true);
            }
        }
        if (bopit == true) {
            ivBopit.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Acierto();
                    //Mensaje("Muy Bien");
                    bopit = false;
                    Inicio(true);
                    return true;
                }
            });

        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void Inicio(boolean flag) {
        if (flag == true) {
            Log.i("Aleatorio", "Flag" + flag);
            random = (int) (Math.random() * 5) + 1;
            Log.i("Aleatorio", "random" + random);
            switch (random) {
                case 1:
                    MediaPlayer instruccion = MediaPlayer.create(this, R.raw.derecha);//reproduce sonido
                    instruccion.start();
                    Mensaje("¡Derecha!");
                    derecha = true;
                    break;
                case 2:
                    instruccion = MediaPlayer.create(this, R.raw.izquierda);//reproduce sonido
                    instruccion.start();
                    Mensaje("¡Izquierda!");
                    izquierda = true;
                    break;
                case 3:
                    instruccion = MediaPlayer.create(this, R.raw.atras);//reproduce sonido
                    instruccion.start();
                    Mensaje("¡Atras!");
                    atras = true;
                    break;
                case 4:
                    instruccion = MediaPlayer.create(this, R.raw.adelante);//reproduce sonido
                    instruccion.start();
                    Mensaje("¡Adelante!");
                    adelante = true;
                    break;
                case 5:
                    instruccion = MediaPlayer.create(this, R.raw.bopit);//reproduce sonido
                    instruccion.start();
                    Mensaje("¡Bop It!");
                    bopit = true;
            }
        }

    }



    public void Mensaje(String mensaje) {
        Toast.makeText(this, mensaje,Toast.LENGTH_SHORT).show();
    }
}

