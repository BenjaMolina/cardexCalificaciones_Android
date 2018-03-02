package com.example.diego.CardexAlumnos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Resumen extends AppCompatActivity {

    private TextView tTot, tAp, tRe, tPr;
    Cursor cursor1;
    SQLiteDatabase db1;
    MediaPlayer mp;
    MediaPlayer mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        tTot = (TextView)findViewById(R.id.cantMat);
        tAp = (TextView)findViewById(R.id.cantAp);
        tRe = (TextView)findViewById(R.id.cantRep);
        tPr = (TextView)findViewById(R.id.cantCal);

        //obtengo el cursor y la base de datos para usarlos en esta actividad
        cursor1 = MainActivity.cursor;
        db1 = MainActivity.db;

        //cuenta todas las materias registradas en la base de datos
        cursor1 = db1.rawQuery("SELECT * FROM " +DbHelper.TABLE_NAME , null);
        int contMat = cursor1.getCount();
        String cTot = Integer.toString(contMat);
        tTot.setText(cTot);

        //cuenta las materias aprobadas
        cursor1 = db1.rawQuery("SELECT *FROM "+DbHelper.TABLE_NAME+ " WHERE "+DbHelper.CAMPO_CAL+ " BETWEEN "+70+" AND "+100,null);
        int contAp = cursor1.getCount();
        String cAp = Integer.toString(contAp);
        tAp.setText(cAp);

        //cuenta las materias reprobadas
        cursor1 = db1.rawQuery("SELECT *FROM "+DbHelper.TABLE_NAME+ " WHERE "+DbHelper.CAMPO_CAL+ " BETWEEN "+0+" AND "+69,null);
        int contRep = cursor1.getCount();
        String cRep = Integer.toString(contRep);
        tRe.setText(cRep);

        //hace el promedio
        cursor1 = db1.rawQuery("SELECT AVG ("+DbHelper.CAMPO_CAL+ ") FROM "+DbHelper.TABLE_NAME+" WHERE("+DbHelper.CAMPO_CAL+" BETWEEN 70 and 100)",null);
        cursor1.moveToFirst();
        float i = cursor1.getFloat(0);
        tPr.setText(i+"");

        if(contAp > contRep) {
            mp = MediaPlayer.create(this, R.raw.aplausos);
            mp.start();
        }
        else{
            mp2 = MediaPlayer.create(this, R.raw.lose);
            mp2.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
