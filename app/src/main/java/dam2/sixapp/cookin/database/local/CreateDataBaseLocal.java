package dam2.sixapp.cookin.database.local;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CreateDataBaseLocal extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.

    public static final int DATABASE_VERSION = 1;


    public static final String DATABASE_NAME = "CookinLocal.db";

    /*String sqlCreate = "CREATE TABLE RECETAS_FAV (" +
            " idRECETAS INT NOT NULL ," +
            " NOMBRE VARCHAR(100) NOT NULL ," +
            " DESCRIPCION VARCHAR(1500) NOT NULL ," +
            " IMAGEN BLOB NOT NULL ," +
            " DURACION INT NOT NULL ," +
            " DIFICULTAD VARCHAR(45) NOT NULL ," +
            " ZONA VARCHAR(45) NOT NULL ," +
            " VOTOS FLOAT NOT NULL ," +
            " PRIMARY KEY (idRECETAS) )";*/

    String sqlCreate = "CREATE TABLE IF NOT EXISTS RECETAS_FAV ("+
            "IDRECETAS int(11) NOT NULL AUTO_INCREMENT,"+
            "NOMBRE varchar(100) COLLATE utf8_unicode_ci NOT NULL,"+
            "DIFICULTAD varchar(45) COLLATE utf8_unicode_ci NOT NULL,"+
            "DURACION int(11) NOT NULL,"+
            "ZONA varchar(45) COLLATE utf8_unicode_ci NOT NULL,"+
            "IMAGEN varchar(100) COLLATE utf8_unicode_ci NOT NULL,"+
            "VOTOS float NOT NULL,"+
            "DESCRIPCION varchar(1500) COLLATE utf8_unicode_ci NOT NULL,"+
            "NUM_ACABADAS int(11) NOT NULL DEFAULT '0',"+
            "PRIMARY KEY (IDRECETAS),"+
            "UNIQUE KEY idRECETAS_UNIQUE (IDRECETAS)"+
            ") ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=24" ;

    String sqlCreate2 = "CREATE TABLE IF NOT EXISTS RECETAS_PEN ("+
            "IDRECETAS int(11) NOT NULL AUTO_INCREMENT,"+
            "NOMBRE varchar(100) COLLATE utf8_unicode_ci NOT NULL,"+
            "DIFICULTAD varchar(45) COLLATE utf8_unicode_ci NOT NULL,"+
            "DURACION int(11) NOT NULL,"+
            "ZONA varchar(45) COLLATE utf8_unicode_ci NOT NULL,"+
            "IMAGEN varchar(100) COLLATE utf8_unicode_ci NOT NULL,"+
            "VOTOS float NOT NULL,"+
            "DESCRIPCION varchar(1500) COLLATE utf8_unicode_ci NOT NULL,"+
            "NUM_ACABADAS int(11) NOT NULL DEFAULT '0',"+
            "PRIMARY KEY (IDRECETAS),"+
            "UNIQUE KEY idRECETAS_UNIQUE (IDRECETAS)"+
            ") ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=24" ;


    String sqlCreate3 = "CREATE TABLE IF NOT EXISTS RECETAS_DONE ("+
            "IDRECETAS int(11) NOT NULL AUTO_INCREMENT,"+
            "NOMBRE varchar(100) COLLATE utf8_unicode_ci NOT NULL,"+
            "DIFICULTAD varchar(45) COLLATE utf8_unicode_ci NOT NULL,"+
            "DURACION int(11) NOT NULL,"+
            "ZONA varchar(45) COLLATE utf8_unicode_ci NOT NULL,"+
            "IMAGEN varchar(100) COLLATE utf8_unicode_ci NOT NULL,"+
            "VOTOS float NOT NULL,"+
            "DESCRIPCION varchar(1500) COLLATE utf8_unicode_ci NOT NULL,"+
            "NUM_ACABADAS int(11) NOT NULL DEFAULT '0',"+
            "PRIMARY KEY (IDRECETAS),"+
            "UNIQUE KEY idRECETAS_UNIQUE (IDRECETAS)"+
            ") ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=24" ;


    public CreateDataBaseLocal(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.d("dbVer", String.valueOf(DATABASE_VERSION));
            Log.d("dbName",DATABASE_NAME);
            Log.d("Conected?","true");
    }


    public void onCreate(SQLiteDatabase db) {
//db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate2);
        db.execSQL(sqlCreate3);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// This database is only a cache for online data, so its upgrade policy is
// to simply to discard the data and start over
//Se elimina la versión anterior de la tabla
//db.execSQL("DROP TABLE IF EXISTS Usuarios");

//Se crea la nueva versión de la tabla
//db.execSQL(sqlCreate);
    }
//public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// //onUpgrade(db, oldVersion, newVersion);
//}
}