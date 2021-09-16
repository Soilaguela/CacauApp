package pt.ipg.cacauapp;

import android.content.ContentValues;
import android.database.Cursor;

public class TipoCacau {
    private long id;
    private String titulo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ContentValues toContentValues(){
        ContentValues valores = new ContentValues();

        valores.put(TabelaTipoCacau.CAMPO_TITULO, titulo);

        return valores;
    }

    public static TipoCacau fromCursor(Cursor cursor) {
        TipoCacau tipoCacau = new TipoCacau();

        tipoCacau.id = cursor.getLong(cursor.getColumnIndex(TabelaTipoCacau._ID));
        tipoCacau.titulo = cursor.getString(cursor.getColumnIndex(TabelaTipoCacau.CAMPO_TITULO));

        return tipoCacau;
    }
}
