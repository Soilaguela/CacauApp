package pt.ipg.cacauapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class TabelaTipoCacau implements BaseColumns {
    public  static final String NOME_TABELA = "ripocacau";
    public  static  final String CAMPO_TITULO = "titulo";// ex:Crioulo, Trimitario, forasteiro

    public static final String[] TODOS_CAMPOS = new String[] { _ID, CAMPO_TITULO };

    private SQLiteDatabase bd;

    public  TabelaTipoCacau(SQLiteDatabase bd){this.bd = bd;}

    public void criar() {
        bd.execSQL(
                "CREATE TABLE " + NOME_TABELA + " ("+
               _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
               CAMPO_TITULO + " TEXT NOT NULL" +
                ")"
        );
    }

    public long insert(ContentValues values) {
        return bd.insert(NOME_TABELA, null, values);
    }

    public int update(long id, ContentValues values) {
        return bd.update(
                NOME_TABELA,
                values,
                _ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    public int delete(long id) {
        return bd.delete(
                NOME_TABELA,
                _ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return bd.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
}
