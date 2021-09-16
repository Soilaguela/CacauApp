package pt.ipg.cacauapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class TabelaVenda implements BaseColumns {
    public static final String NOME_TABELA = "venda";
    public  static final String CAMPO_DATA = "data";
    public  static final String CAMPO_QUILOS = "quilos";
    public static final String ORDENAR_CAMPO_QUILOS = NOME_TABELA + "." + CAMPO_QUILOS;
    public static final String CAMPO_ID_TIPOCACAU = "idTipoCacau";
    public static final String CAMPO_TIPOCACAU = "tipoCacau";

    public static final String[] TODOS_CAMPOS_TABELA = new String[] { _ID, CAMPO_QUILOS, CAMPO_DATA, CAMPO_ID_TIPOCACAU };
    public static final String[] TODOS_CAMPOS = new String[] { _ID, CAMPO_QUILOS,CAMPO_DATA, CAMPO_ID_TIPOCACAU, CAMPO_TIPOCACAU };

    private SQLiteDatabase db;
    public TabelaVenda(SQLiteDatabase db){
        this.db = db;
    }

    public void cria(){
        db.execSQL(
                "CREATE TABLE " + NOME_TABELA + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        CAMPO_DATA + " TEXT NOT NULL," +
                        CAMPO_ID_TIPOCACAU + " INTEGER NOT NULL," +
                        CAMPO_QUILOS + " TEXT," +
                        "FOREIGN KEY (" + CAMPO_ID_TIPOCACAU + ") REFERENCES "
                        + TabelaVenda.NOME_TABELA + "(" + TabelaVenda._ID + ")" +
                        ")"
        );
    }

    public long insert(ContentValues values) {
        return db.insert(NOME_TABELA, null, values);
    }

    public int update(long id, ContentValues values) {
        return db.update(
                NOME_TABELA,
                values,
                _ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    public int delete(long id) {
        return db.delete(
                NOME_TABELA,
                _ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        int posCampoOrador = -1; // -1 significa que esta coluna não é necessária

        for (int i = 0; i < columns.length; i++) {
            if (columns[i].equals(CAMPO_TIPOCACAU)) {
                posCampoOrador = i;
                break;
            }
        }

        if (posCampoOrador != -1) {
            String sql = "SELECT ";

            for (int i = 0; i < columns.length; i++) {
                String coluna = columns[i];

                if (i == posCampoOrador) {
                    coluna = TabelaTipoCacau.NOME_TABELA + "." + TabelaTipoCacau.CAMPO_TITULO + " AS " + coluna;
                } else {
                    coluna = NOME_TABELA + "." + coluna;
                }

                if (i != 0) {
                    sql += ", ";
                }
                sql += coluna;
            }

            sql += " FROM " + NOME_TABELA + " INNER JOIN " + TabelaTipoCacau.NOME_TABELA;
            sql += " ON " + TabelaTipoCacau.NOME_TABELA + "." + TabelaTipoCacau._ID + "=" + NOME_TABELA + "." + CAMPO_ID_TIPOCACAU;

            if (selection != null) {
                sql += " WHERE " + selection;
            }

            if (groupBy != null) {
                sql += " GROUP BY " + groupBy;

                if (having != null) {
                    sql += " HAVING " + having;
                }
            }

            if (orderBy != null) {
                sql += " ORDER BY " + orderBy;
            }

            return db.rawQuery(sql, selectionArgs);
        } else {
            return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy);
        }
    }
}
