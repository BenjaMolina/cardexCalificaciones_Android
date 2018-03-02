package com.example.diego.CardexAlumnos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{
    //Informacion de la base de datos
    private static final String DATABASE_NAME = "db_CardexAlumno";
    private static final int DATABASE_VERSION = 5;

    //Tabla y campos
    public static final String TABLE_NAME = "cedula";
    public static final String CAMPO_CLAVE = "_id";
    public static final String CAMPO_MATERIA = "materia";
    public static final String CAMPO_CREDITOS = "creditos";
    public static final String CAMPO_SEMESTRE = "semestre";
    public static final String CAMPO_CAL = "calificacion";

    //Referencia al contexto
    private Context mContext;

    //Constructor
    public DbHelper(Context contexto){
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_NAME +" ("
                + CAMPO_CLAVE + " TEXT PRIMARY KEY, "
                + CAMPO_MATERIA + " TEXT, "
                + CAMPO_CREDITOS + " INTEGER, "
                + CAMPO_SEMESTRE + " INTEGER, "
                + CAMPO_CAL + " INTEGER);";

        db.execSQL(query);

        ContentValues cv = new ContentValues();

        //Se agregan las materioas
        cv.put(CAMPO_CLAVE, "ACF0901");
        cv.put(CAMPO_MATERIA, "Calc. Difer.");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 1);
        cv.put(CAMPO_CAL, 96);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1008");
        cv.put(CAMPO_MATERIA, "Fund. de Progr.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 1);
        cv.put(CAMPO_CAL, 98);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "ACA0907");
        cv.put(CAMPO_MATERIA, "Taller de Ética");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 1);
        cv.put(CAMPO_CAL, 98);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "AEF1041");
        cv.put(CAMPO_MATERIA, "Mat. Discretas");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 1);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCH1024");
        cv.put(CAMPO_MATERIA, "Taller de Admon.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 1);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "ACC0906");
        cv.put(CAMPO_MATERIA, "Fund. de Inv.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 1);
        cv.put(CAMPO_CAL, 96);
        db.insert(TABLE_NAME, null, cv);

        //---------------MATERIAS DE SEGUNDO SEMESTRE-------//
        cv.put(CAMPO_CLAVE, "ACF0902");
        cv.put(CAMPO_MATERIA, "Calc. Integral");
        cv.put(CAMPO_CREDITOS, 6);
        cv.put(CAMPO_SEMESTRE, 2);
        cv.put(CAMPO_CAL, 71);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1020");
        cv.put(CAMPO_MATERIA, "Program. O.O");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 2);
        cv.put(CAMPO_CAL, 90);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "AEC1008");
        cv.put(CAMPO_MATERIA, "Contab. Financ.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 2);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "AEC1058");
        cv.put(CAMPO_MATERIA, "Química");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 2);
        cv.put(CAMPO_CAL, 86);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "ACF0903");
        cv.put(CAMPO_MATERIA, "Algebra Lineal");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 2);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "AEF1052");
        cv.put(CAMPO_MATERIA, "Prob. y Estad.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 2);
        cv.put(CAMPO_CAL, 80);
        db.insert(TABLE_NAME, null, cv);

        db.insert(TABLE_NAME, null, cv);


        //-----------MATERIAS DE TERCER SEMESTRE ------------//
        cv.put(CAMPO_CLAVE, "ACF0904");
        cv.put(CAMPO_MATERIA, "Calc. Vectorial");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 3);
        cv.put(CAMPO_CAL, 90);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "AED1026");
        cv.put(CAMPO_MATERIA, "Est. Datos");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 3);
        cv.put(CAMPO_CAL, 98);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "AEC1061");
        cv.put(CAMPO_MATERIA, "Sist. Op.");
        cv.put(CAMPO_CREDITOS, 6);
        cv.put(CAMPO_SEMESTRE, 3);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCF1006");
        cv.put(CAMPO_MATERIA, "Física Gral.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 3);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCC1005");
        cv.put(CAMPO_MATERIA, "Cult. Empresarial");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 3);
        cv.put(CAMPO_CAL, 95);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCC1013");
        cv.put(CAMPO_MATERIA, "Inv. de Oper.");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 3);
        cv.put(CAMPO_CAL, 98);
        db.insert(TABLE_NAME, null, cv);

        //------4TO SEMESTTE---------------//

        cv.put(CAMPO_CLAVE, "ACF0905");
        cv.put(CAMPO_MATERIA, "Ec. Diferenciales");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 4);
        cv.put(CAMPO_CAL, 90);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "AEF1031");
        cv.put(CAMPO_MATERIA, "Fund. de BD");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 4);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCA1026");
        cv.put(CAMPO_MATERIA, "Taller de SO");
        cv.put(CAMPO_CREDITOS, 6);
        cv.put(CAMPO_SEMESTRE, 4);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCC1017");
        cv.put(CAMPO_MATERIA, "Met. Numéricos");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 4);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1018");
        cv.put(CAMPO_MATERIA, "Princ. Electr.");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 4);
        cv.put(CAMPO_CAL, 94);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1027");
        cv.put(CAMPO_MATERIA, "Top. Avanz. Prog.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 4);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);


        //--------------QUINTO SEMESTRE -------//
        cv.put(CAMPO_CLAVE, "AEC1034");
        cv.put(CAMPO_MATERIA, "Fund. de Telec.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 5);
        cv.put(CAMPO_CAL, 90);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "ACD0908");
        cv.put(CAMPO_MATERIA, "Des. Sustentable");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 5);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCA1025");
        cv.put(CAMPO_MATERIA, "Taller de BD");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 5);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCC1007");
        cv.put(CAMPO_MATERIA, "Fund. de Ing. Soft.");
        cv.put(CAMPO_CREDITOS, 6);
        cv.put(CAMPO_SEMESTRE, 5);
        cv.put(CAMPO_CAL, 96);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1003");
        cv.put(CAMPO_MATERIA, "Arq. de Comp.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 5);
        cv.put(CAMPO_CAL, 94);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1022");
        cv.put(CAMPO_MATERIA, "Simulación");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 5);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);


        //--SEXTO SEMESTRE------//
        cv.put(CAMPO_CLAVE, "SCB1001");
        cv.put(CAMPO_MATERIA, "Admon. de BD");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 6);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCC1010");
        cv.put(CAMPO_MATERIA, "Graficación");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 6);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCC1014");
        cv.put(CAMPO_MATERIA, "Leng. de Interfaz");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 6);
        cv.put(CAMPO_CAL, 93);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1011");
        cv.put(CAMPO_MATERIA, "Ing. de Soft.");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 6);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1015");
        cv.put(CAMPO_MATERIA, "Leng y Autom. I");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 6);
        cv.put(CAMPO_CAL, 94);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1021");
        cv.put(CAMPO_MATERIA, "Redes de Comp.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 6);
        cv.put(CAMPO_CAL, 96);
        db.insert(TABLE_NAME, null, cv);

        //-----------SEPTIMO SEMESTRE ------//
        cv.put(CAMPO_CLAVE, "ACA0909");
        cv.put(CAMPO_MATERIA, "Taller de Inv. I");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 7);
        cv.put(CAMPO_CAL, 97);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "AMD1503");
        cv.put(CAMPO_MATERIA, "Prog. para Android");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 7);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "AMF1501");
        cv.put(CAMPO_MATERIA, "Form y Eval. de Proy.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 7);
        cv.put(CAMPO_CAL, 98);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1004");
        cv.put(CAMPO_MATERIA, "Comn. y Enrut. Red.");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 7);
        cv.put(CAMPO_CAL, 93);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1016");
        cv.put(CAMPO_MATERIA, "Leng. y Aut. II");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 7);
        cv.put(CAMPO_CAL, 90);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCD1023");
        cv.put(CAMPO_MATERIA, "Sist. Programables");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 7);
        cv.put(CAMPO_CAL, 85);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCG1009");
        cv.put(CAMPO_MATERIA, "Gest. Proy. Soft.");
        cv.put(CAMPO_CREDITOS, 6);
        cv.put(CAMPO_SEMESTRE, 7);
        cv.put(CAMPO_CAL, 100);
        db.insert(TABLE_NAME, null, cv);

        //-------MATERIAS DE OCTAVO SEMESTRE----------

        cv.put(CAMPO_CLAVE, "SCG1019");
        cv.put(CAMPO_MATERIA, "Prog. Log y Func.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 8);
        cv.put(CAMPO_CAL, 0);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "SCG1002");
        cv.put(CAMPO_MATERIA, "Admon. de Redes");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 8);
        cv.put(CAMPO_CAL, 0);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "ACA0910");
        cv.put(CAMPO_MATERIA, "Taller. Inv. II");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 8);
        cv.put(CAMPO_CAL, 0);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "AEB1055");
        cv.put(CAMPO_MATERIA, "Progr. Web");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 8);
        cv.put(CAMPO_CAL, 0);
        db.insert(TABLE_NAME, null, cv);

        cv.put(CAMPO_CLAVE, "AMD1502");
        cv.put(CAMPO_MATERIA, "Aplic. Mov y S. Web");
        cv.put(CAMPO_CREDITOS, 6);
        cv.put(CAMPO_SEMESTRE, 8);
        cv.put(CAMPO_CAL, 0);
        db.insert(TABLE_NAME,null,cv);

        cv.put(CAMPO_CLAVE, "AMD1504");
        cv.put(CAMPO_MATERIA, "Progr. Para iOS");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 8);
        cv.put(CAMPO_CAL, 0);
        db.insert(TABLE_NAME,null,cv);

        cv.put(CAMPO_CLAVE, "AMD1505");
        cv.put(CAMPO_MATERIA, "Top. Av. de Tec Web");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 8);
        cv.put(CAMPO_CAL, 0);
        db.insert(TABLE_NAME,null,cv);


        //-------MATERIAS DE NOVENO SEMESTRE----------
        cv.put(CAMPO_CLAVE, "SCC1012");
        cv.put(CAMPO_MATERIA, "Intel. Artif.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 9);
        cv.put(CAMPO_CAL, 0);
        db.insert(TABLE_NAME,null,cv);

        cv.put(CAMPO_CLAVE, "SERVSOC");
        cv.put(CAMPO_MATERIA, "Serv. Social");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 9);
        cv.put(CAMPO_CAL, 0);
        db.insert(TABLE_NAME,null,cv);

        cv.put(CAMPO_CLAVE, "ACOM");
        cv.put(CAMPO_MATERIA, "Act. Comp.");
        cv.put(CAMPO_CREDITOS, 4);
        cv.put(CAMPO_SEMESTRE, 9);
        cv.put(CAMPO_CAL, 0);
        db.insert(TABLE_NAME,null,cv);

        cv.put(CAMPO_CLAVE, "Residesc");
        cv.put(CAMPO_MATERIA, "Act. Comp.");
        cv.put(CAMPO_CREDITOS, 5);
        cv.put(CAMPO_SEMESTRE, 9);
        cv.put(CAMPO_CAL, 0);
        db.insert(TABLE_NAME,null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
