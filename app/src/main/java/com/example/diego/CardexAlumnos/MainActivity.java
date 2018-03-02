package com.example.diego.CardexAlumnos;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    //Declara las constantes para los menu
    private static final int ASIGNAR_CAL_ID = Menu.FIRST+1;
    private static final int BORRAR_CAL_ID = Menu.FIRST+2;
    private static final int FILTRA_SEMESTRE_ID = Menu.FIRST+3;
    private static final int FILTRA_RANGO_ID = Menu.FIRST+4;
    private static final int FILTRA_APR_ID = Menu.FIRST+5;
    private static final int FILTRA_REP_ID = Menu.FIRST+6;
    private static final int FILTRA_MATERIA_ID = Menu.FIRST+7;
    private static final int FILTRA_TODO_ID = Menu.FIRST+8;
    private static final int FILTRAR_ID = Menu.FIRST+9;
    private static final int ORDENAR_MATERIA = Menu.FIRST+10;
    private static final int ORDENAR_SEMESTRE = Menu.FIRST+11;
    private static final int ORDENAR_CALIFICACION = Menu.FIRST+12;
    private static final int ORDENAR = Menu.FIRST+13;
    private static final int RESUMEN_ID = Menu.FIRST+14;
    private static final int ACERCA_ID = Menu.FIRST+15;
    private static final int SALIR_ID = Menu.FIRST+16;
    /*private static final int ORDENAR_MATERIA = Menu.FIRST+13;
    private static final int ORDENAR_SEMESTRE = Menu.FIRST+14;
    private static final int ORDENAR_CALIFICACION = Menu.FIRST+15;
    private static final int ORDENAR = Menu.FIRST+16;*/


    //Declara variables relacionadas con las vistas
    static SQLiteDatabase db;
    private ListAdapter adapter;
    static Cursor cursor;
    private ListView mListView;
    private String llaveCursor; //para modificar datos relacionados con la materia
    private TextView ordCla;
            //ordMat, ordCre, ordSem, ordCal;
    public static int contMat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instanciar la base de datos
        db = new DbHelper(this).getWritableDatabase();

        //Se vacia el contenido de la BD al cursor
        cursor = db.rawQuery("SELECT * FROM "+ DbHelper.TABLE_NAME + " ORDER BY _id",null);

        //Asociar el adaptador con el cursor y el layout
        /*adapter = new SimpleCursorAdapter(
                this, //el contexto de la aplicacion
                R.layout.row, //el layout para los renglones
                cursor, //resultado de la consulta
                new String[]{DbHelper.CAMPO_CLAVE, DbHelper.CAMPO_MATERIA, DbHelper.CAMPO_CREDITOS, DbHelper.CAMPO_SEMESTRE, DbHelper.CAMPO_CAL},
                new int[]{R.id.r_clave, R.id.r_materia, R.id.r_creditos, R.id.r_semestre, R.id.r_cal}, 0){


        };*/
        adapter = new AdaptadorPersonalizado(getApplicationContext(),cursor);


        //Se asocia el adaptador con la vista
        mListView = (ListView) findViewById(R.id.lista);
        mListView.setAdapter(adapter);



        //Se registra el menu contextual
        registerForContextMenu(mListView);

        ordCla = (TextView)findViewById(R.id.bclabe);
        //ordCre = (TextView)findViewById(R.id.bcreditos);
        //ordSem = (TextView)findViewById(R.id.bsemestre);
        //ordCal = (TextView)findViewById(R.id.bcal);


    }

    public class AdaptadorPersonalizado extends CursorAdapter{

        private LayoutInflater inflador;
        TextView AcvMateria, Amat, Acreditos,Asemestre,Acalif;

        public AdaptadorPersonalizado(Context contexto,Cursor c){
            super(contexto,c,false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            inflador=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View vista=inflador.inflate(R.layout.row,parent,false);
            return vista;
        }

        @Override
        public void changeCursor(Cursor cursor) {
            super.changeCursor(cursor);
        }

        @Override
        public void bindView(View view, Context context, Cursor c) {

            AcvMateria=(TextView) view.findViewById(R.id.r_clave);
            Amat=(TextView)view.findViewById(R.id.r_materia);
            Acreditos=(TextView)view.findViewById(R.id.r_creditos);
            Asemestre=(TextView)view.findViewById(R.id.r_semestre);
            Acalif=(TextView)view.findViewById(R.id.r_cal);

            //Agregando los valores de la BD a los View
            AcvMateria.setText(c.getString(c.getColumnIndex(DbHelper.CAMPO_CLAVE)));
            Amat.setText(c.getString(c.getColumnIndex(DbHelper.CAMPO_MATERIA)));
            Acreditos.setText(c.getString(c.getColumnIndex(DbHelper.CAMPO_CREDITOS)));
            Asemestre.setText(c.getString(c.getColumnIndex(DbHelper.CAMPO_SEMESTRE)));

            if(c.getString(c.getColumnIndex(DbHelper.CAMPO_CAL))!=null){
                if(Integer.parseInt(c.getString(c.getColumnIndex(DbHelper.CAMPO_CAL)))<70 && Integer.parseInt(c.getString(c.getColumnIndex(DbHelper.CAMPO_SEMESTRE)))!=0){
                    Acalif.setText("NA");
                    Acalif.setTextColor(Color.RED);
                    AcvMateria.setTextColor(Color.RED);
                    Amat.setTextColor(Color.RED);
                    Acreditos.setTextColor(Color.RED);
                    Asemestre.setTextColor(Color.RED);
                }
                else if(Integer.parseInt(c.getString(c.getColumnIndex(DbHelper.CAMPO_CAL)))==0 && Integer.parseInt(c.getString(c.getColumnIndex(DbHelper.CAMPO_SEMESTRE)))==0){
                    Acalif.setText("NC");
                    Acalif.setTextColor(Color.BLUE);
                    AcvMateria.setTextColor(Color.BLUE);
                    Amat.setTextColor(Color.BLUE);
                    Acreditos.setTextColor(Color.BLUE);
                    Asemestre.setTextColor(Color.BLUE);
                }
                else{
                    Acalif.setText(c.getString(c.getColumnIndex(DbHelper.CAMPO_CAL)));
                    Acalif.setTextColor(Color.BLACK);
                    AcvMateria.setTextColor(Color.BLACK);
                    Amat.setTextColor(Color.BLACK);
                    Acreditos.setTextColor(Color.BLACK);
                    Asemestre.setTextColor(Color.BLACK);
                }
            }
        }


    }


    //Se crea el menu principal
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu smenuf = menu.addSubMenu(Menu.NONE,FILTRAR_ID,Menu.NONE,"Filtrar");
        smenuf.add(Menu.NONE,FILTRA_SEMESTRE_ID,Menu.NONE,"Por semestre");
        smenuf.add(Menu.NONE,FILTRA_RANGO_ID,Menu.NONE,"Por rango");
        smenuf.add(Menu.NONE,FILTRA_APR_ID,Menu.NONE,"Por aprobadas");
        smenuf.add(Menu.NONE,FILTRA_REP_ID,Menu.NONE,"Por reprobadas");
        smenuf.add(Menu.NONE,FILTRA_MATERIA_ID,Menu.NONE,"Por materia");
        smenuf.add(Menu.NONE,FILTRA_TODO_ID,Menu.NONE,"Mostrar todo");

        SubMenu smenuf2 = menu.addSubMenu(Menu.NONE,ORDENAR,Menu.NONE,"Ordenar");
        smenuf2.add(Menu.NONE,ORDENAR_MATERIA,Menu.NONE,"Por Materia");
        smenuf2.add(Menu.NONE,ORDENAR_SEMESTRE,Menu.NONE,"Por Semestre");
        smenuf2.add(Menu.NONE,ORDENAR_CALIFICACION,Menu.NONE,"Por Calificacion");

        menu.add(Menu.NONE,RESUMEN_ID,Menu.NONE,"Resumen");
        menu.add(Menu.NONE,ACERCA_ID,Menu.NONE,"Acerca de");
        menu.add(Menu.NONE,SALIR_ID,Menu.NONE,"Salir");


        return super.onCreateOptionsMenu(menu);
    }


    private void ordenaMateria(){
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME + " ORDER BY " + DbHelper.CAMPO_MATERIA, null);
        ((AdaptadorPersonalizado) adapter).changeCursor(cursor);
    }


    private void ordenaSemestre() {
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME + " ORDER BY " + DbHelper.CAMPO_SEMESTRE, null);
        ((AdaptadorPersonalizado) adapter).changeCursor(cursor);
    }


    private void ordenaCalificacion() {
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME + " ORDER BY " + DbHelper.CAMPO_CAL, null);
        ((AdaptadorPersonalizado) adapter).changeCursor(cursor);
    }



    //Acciones que se realizaran al seleccionar una opción
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case SALIR_ID:
                System.exit(0);
                break;

            case ACERCA_ID: {
                Intent intent1 = new Intent(this, AcercaDe.class);
                startActivity(intent1);
                break;
            }

            case RESUMEN_ID: {
                Intent intent2 = new Intent(this, Resumen.class);
                startActivity(intent2);
                break;
            }

            case FILTRA_SEMESTRE_ID: {
                filtrarSemestre(this);
                break;
            }

            case FILTRA_MATERIA_ID: {
                filtraMateria(this);
                break;
            }

            case FILTRA_APR_ID: {
                filtraAprobada(this);
                break;
            }

            case FILTRA_REP_ID: {
                filtraReprobada(this);
                break;
            }

            case FILTRA_RANGO_ID: {
                filtraRango(this);
                break;
            }

            case FILTRA_TODO_ID: {
                filtraTodo(this);
                break;
            }

            case ORDENAR_MATERIA:{
                ordenaMateria();
                break;
            }


            case ORDENAR_SEMESTRE:{
                ordenaSemestre();
                break;
            }

            case ORDENAR_CALIFICACION:{
                ordenaCalificacion();
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    //Se genera el menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(Menu.NONE,ASIGNAR_CAL_ID,Menu.NONE,"Modificar");
        menu.add(Menu.NONE,BORRAR_CAL_ID,Menu.NONE,"Eliminar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        switch (item.getItemId()){

            case ASIGNAR_CAL_ID: {
                //Obtiene la llave del renglón seleccionado
                cursor.moveToPosition(info.position);
                int numColumnaCursor = cursor.getColumnIndex(DbHelper.CAMPO_CLAVE);
                llaveCursor = cursor.getString(numColumnaCursor);
                modificar(llaveCursor);
                break;
            }

            case BORRAR_CAL_ID: {
                //Obtiene la llave del renglón seleccionado
                cursor.moveToPosition(info.position);
                int numColumnaCursor = cursor.getColumnIndex(DbHelper.CAMPO_CLAVE);
                llaveCursor = cursor.getString(numColumnaCursor);
                borrar(llaveCursor);
                break;
            }
        }
        return super.onContextItemSelected(item);
    }


    private void borrar(final String llaveCursor){
        //Realiza la consulta del registro a eliminar
        Cursor c1 = db.rawQuery("SELECT *FROM " + DbHelper.TABLE_NAME+ " WHERE "+DbHelper.CAMPO_CLAVE+ "=?",new String[]{llaveCursor});
        c1.moveToFirst(); //Se coloca en el primer elemento del cursor

        String clave = c1.getString(c1.getColumnIndex(DbHelper.CAMPO_CLAVE));
        String mat = c1.getString(c1.getColumnIndex(DbHelper.CAMPO_MATERIA));
        String cal = c1.getString(c1.getColumnIndex(DbHelper.CAMPO_CAL));
        String sem = c1.getString(c1.getColumnIndex(DbHelper.CAMPO_SEMESTRE));

        LayoutInflater inflater = LayoutInflater.from(this);
        View actualizarView = inflater.inflate(R.layout.modificar,null);

        //se colocan los valores para la vista
        final TextView campoClave = (TextView) actualizarView.findViewById(R.id.m_clave);
        campoClave.setText(clave);
        campoClave.setEnabled(false);

        final TextView campoMat = (TextView) actualizarView.findViewById(R.id.m_materia);
        campoMat.setText(mat);
        campoMat.setEnabled(false);

        final TextView campoCal = (TextView) actualizarView.findViewById(R.id.m_cal);
        campoCal.setText(cal);
        campoCal.setEnabled(false);

        final TextView campoSem = (TextView) actualizarView.findViewById(R.id.m_semestre);
        campoSem.setText(sem);
        campoSem.setEnabled(false);

        //Lanza el dialogo para la eliminación
        new AlertDialog.Builder(this)
                .setTitle("¿Desea eliminar el semestre y la calificación?")
                .setView(actualizarView)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContentValues cv = new ContentValues();
                        cv.put(DbHelper.CAMPO_SEMESTRE, 0);
                        cv.put(DbHelper.CAMPO_CAL, 0);
                        String eMat = campoMat.getText().toString();

                        db.update(DbHelper.TABLE_NAME,cv,DbHelper.CAMPO_CLAVE+"=?", new String[]{llaveCursor});

                        cursor = db.rawQuery("SELECT * FROM "+ DbHelper.TABLE_NAME + " ORDER BY " + DbHelper.CAMPO_SEMESTRE, null);
                        ((AdaptadorPersonalizado)adapter).changeCursor(cursor);
                        Toast.makeText(getApplicationContext(), "Datos de "+eMat+" han sido eliminados", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    private void modificar(final String llaveCursor){
        //Realiza la consulta del registro a modificar

        Cursor c1 = db.rawQuery("SELECT *FROM " + DbHelper.TABLE_NAME+ " WHERE "+DbHelper.CAMPO_CLAVE+ "=?",new String[]{llaveCursor});
        c1.moveToFirst(); //Se coloca en el primer elemento del cursor

        String clave = c1.getString(c1.getColumnIndex(DbHelper.CAMPO_CLAVE));
        String mat = c1.getString(c1.getColumnIndex(DbHelper.CAMPO_MATERIA));
        String cal = c1.getString(c1.getColumnIndex(DbHelper.CAMPO_CAL));
        String sem = c1.getString(c1.getColumnIndex(DbHelper.CAMPO_SEMESTRE));

        LayoutInflater inflater = LayoutInflater.from(this);
        View actualizarView = inflater.inflate(R.layout.modificar,null);


        //se colocan los valores para la vista
        final TextView campoClave = (TextView) actualizarView.findViewById(R.id.m_clave);
        campoClave.setText(clave);
        campoClave.setEnabled(false);

        final TextView campoMat = (TextView) actualizarView.findViewById(R.id.m_materia);
        campoMat.setText(mat);
        campoMat.setEnabled(false);

        final TextView campoCal = (TextView) actualizarView.findViewById(R.id.m_cal);
        campoCal.setText(cal);

        final TextView campoSem = (TextView) actualizarView.findViewById(R.id.m_semestre);
        campoSem.setText(sem);

        final TextView tvCal = (TextView) actualizarView.findViewById(R.id.tv_mcal);
        final TextView tvSem = (TextView) actualizarView.findViewById(R.id.tv_msemestre);


        final RadioButton rbCursado = (RadioButton) actualizarView.findViewById(R.id.rbcursado);
        rbCursado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCal.setVisibility(View.VISIBLE);
                tvSem.setVisibility(View.VISIBLE);
                campoCal.setVisibility(View.VISIBLE);
                campoSem.setVisibility(View.VISIBLE);
            }
        });


        RadioButton rbNoCursado = (RadioButton) actualizarView.findViewById(R.id.rbnoCursado);
        rbNoCursado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCal.setVisibility(View.INVISIBLE);
                tvSem.setVisibility(View.INVISIBLE);
                campoCal.setVisibility(View.INVISIBLE);
                campoSem.setVisibility(View.INVISIBLE);
            }
        });

        if(cal.equalsIgnoreCase("0") && sem.equalsIgnoreCase("0")){ //Es no cursado
            tvCal.setVisibility(View.INVISIBLE);
            tvSem.setVisibility(View.INVISIBLE);
            campoCal.setVisibility(View.INVISIBLE);
            campoSem.setVisibility(View.INVISIBLE);
            rbCursado.setChecked(false);
            rbNoCursado.setChecked(true);

        }

        else{
            tvCal.setVisibility(View.VISIBLE);
            tvSem.setVisibility(View.VISIBLE);
            campoCal.setVisibility(View.VISIBLE);
            campoSem.setVisibility(View.VISIBLE);
            rbCursado.setChecked(true);
            rbNoCursado.setChecked(false);
        }

        //Lanza el dialogo para la modificación
        new AlertDialog.Builder(this)
                .setTitle("Modificar datos")
                .setView(actualizarView)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContentValues cv = new ContentValues();

                        if(rbCursado.isChecked()) { //Si la materia ya es cursada
                            cv.put(DbHelper.CAMPO_SEMESTRE, campoSem.getText().toString());
                            cv.put(DbHelper.CAMPO_CAL, campoCal.getText().toString());

                            int comp = Integer.parseInt(campoCal.getText().toString());

                            if (comp < 70) {
                                cv.put(DbHelper.CAMPO_CAL, 0);
                            }


                            String eCal = campoCal.getText().toString();
                            String eSem = campoSem.getText().toString();
                            String eMat = campoMat.getText().toString();
                            int c = Integer.parseInt(eCal);
                            int s = Integer.parseInt(eSem);

                            if (s > 12 || c > 100 || s==0) {
                                Toast.makeText(getApplicationContext(), "Ingrese datos válidos", Toast.LENGTH_SHORT).show();
                            } else {
                                db.update(DbHelper.TABLE_NAME, cv, DbHelper.CAMPO_CLAVE + "=?", new String[]{llaveCursor});
                                cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME + " ORDER BY " + DbHelper.CAMPO_SEMESTRE, null);
                                ((AdaptadorPersonalizado) adapter).changeCursor(cursor);
                                Toast.makeText(getApplicationContext(), "Datos de " + eMat + " han sido modificados", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else{ //Materia No Cursada
                            cv.put(DbHelper.CAMPO_SEMESTRE, 0);
                            cv.put(DbHelper.CAMPO_CAL, 0);

                            String eMat = campoMat.getText().toString();

                                db.update(DbHelper.TABLE_NAME, cv, DbHelper.CAMPO_CLAVE + "=?", new String[]{llaveCursor});
                                cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME + " ORDER BY " + DbHelper.CAMPO_SEMESTRE, null);
                                ((AdaptadorPersonalizado) adapter).changeCursor(cursor);
                                Toast.makeText(getApplicationContext(), "Datos de " + eMat + " han sido modificados", Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }


    public void filtrarSemestre(final MainActivity contexto){
        LayoutInflater inflater = LayoutInflater.from(this);
        final View buscarView = inflater.inflate(R.layout.bs,null);


        //Pide los datos
        new AlertDialog.Builder(this).setTitle("Ingrese el semestre").setView(buscarView)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //obtiene el dato
                        EditText sem = (EditText) buscarView.findViewById(R.id.bs_texto);

                        String cadSem = sem.getText().toString();

                        //hace la busqueda del semestre ingresado
                        cursor = db.rawQuery("SELECT *FROM "+DbHelper.TABLE_NAME+ " WHERE "+DbHelper.CAMPO_SEMESTRE+ " LIKE '%"+cadSem+"%' ORDER BY "+DbHelper.CAMPO_SEMESTRE,null);

                        if(cursor.moveToFirst()){
                            //se actualiza la pantalla
                            ((AdaptadorPersonalizado)adapter).changeCursor(cursor);
                        }

                        else
                            Toast.makeText(contexto,"Ingrese un semestre válido",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    public void filtraMateria(final MainActivity contexto){
        LayoutInflater inflater = LayoutInflater.from(this);
        final View buscarView = inflater.inflate(R.layout.buscar,null);

        //Pide los datos
        new AlertDialog.Builder(this).setTitle("Ingrese la clave de la materia").setView(buscarView)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //obtiene el dato
                        EditText mat = (EditText) buscarView.findViewById(R.id.b_texto);
                        String cadMat = mat.getText().toString();

                        //hace la busqueda dela clave ingresada
                        cursor = db.rawQuery("SELECT *FROM "+DbHelper.TABLE_NAME+ " WHERE "+DbHelper.CAMPO_CLAVE+ " LIKE '%"+cadMat+"%' ORDER BY "+DbHelper.CAMPO_CLAVE,null);

                        if(cursor.moveToFirst()){
                            //se actualiza la pantalla
                            ((AdaptadorPersonalizado)adapter).changeCursor(cursor);
                        }

                        else
                            Toast.makeText(contexto,"Ingrese una clave válida",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    public void filtraAprobada(final MainActivity contexto){
        cursor = db.rawQuery("SELECT *FROM "+DbHelper.TABLE_NAME+ " WHERE "+DbHelper.CAMPO_CAL+ " BETWEEN "+70+" AND "+100+" ORDER BY "+DbHelper.CAMPO_CAL,null);
        ((AdaptadorPersonalizado)adapter).changeCursor(cursor);
    }

    public void filtraReprobada(final MainActivity contexto){
        cursor = db.rawQuery("SELECT *FROM "+DbHelper.TABLE_NAME+ " WHERE "+DbHelper.CAMPO_CAL+ " BETWEEN "+1+" AND "+69+" ORDER BY "+DbHelper.CAMPO_CAL,null);
        ((AdaptadorPersonalizado)adapter).changeCursor(cursor);
    }

    //AÑADIR FILTRAR NO CURSADAS

    public void filtraTodo(final MainActivity contexto){
        cursor = db.rawQuery("SELECT * FROM " +DbHelper.TABLE_NAME + " ORDER BY " + DbHelper.CAMPO_SEMESTRE, null);
        ((AdaptadorPersonalizado)adapter).changeCursor(cursor);
    }

    public void filtraRango(final MainActivity contexto){
        LayoutInflater inflater = LayoutInflater.from(this);
        final View buscarView = inflater.inflate(R.layout.buscarango,null);

        //Pide los datos
        new AlertDialog.Builder(this).setTitle("Ingrese el rango").setView(buscarView)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //obtiene el dato
                        EditText ran1 = (EditText) buscarView.findViewById(R.id.b_texto1);
                        String cadRan1 = ran1.getText().toString();

                        EditText ran2 = (EditText) buscarView.findViewById(R.id.b_texto2);
                        String cadRan2 = ran2.getText().toString();

                        //hace la busqueda dela clave ingresada
                        cursor = db.rawQuery("SELECT *FROM "+DbHelper.TABLE_NAME+ " WHERE "+DbHelper.CAMPO_SEMESTRE+ " BETWEEN "+cadRan1+" AND "+cadRan2+" ORDER BY "+DbHelper.CAMPO_SEMESTRE,null);

                        if(cursor.moveToFirst()){
                            //se actualiza la pantalla
                            ((AdaptadorPersonalizado)adapter).changeCursor(cursor);
                        }

                        else
                            Toast.makeText(contexto,"Ingrese un rango válido",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

}
