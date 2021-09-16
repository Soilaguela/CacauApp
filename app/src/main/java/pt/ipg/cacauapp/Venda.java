package pt.ipg.cacauapp;

import android.content.ContentValues;
import android.database.Cursor;

public class Venda {
    private long id;
    private String quilos;
    private String data;
    private long idTipocacau;
    private String tipoCacau;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getQuilos() {
        return quilos;
    }

    public void setQuilos(String quilos) {
        this.quilos = quilos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public long getIdTipoCacau() {
        return idTipocacau;
    }

    public void setIdTipoCacau(long idTipocacau) {
        this.idTipocacau = idTipocacau;
    }

    public String getTipoCacau() {
        return tipoCacau;
    }

    public ContentValues toContentValues() {
        ContentValues valores = new ContentValues();

        valores.put(TabelaVenda.CAMPO_DATA, data);
        valores.put(TabelaVenda.CAMPO_QUILOS, quilos);
        valores.put(TabelaVenda.CAMPO_ID_TIPOCACAU, idTipocacau);

        return valores;
    }

    public static Venda fromCursor(Cursor cursor) {
        Venda venda = new Venda();

        venda.id = cursor.getLong(cursor.getColumnIndex(TabelaVenda._ID));
        venda.data = cursor.getString(cursor.getColumnIndex(TabelaVenda.CAMPO_DATA));
        venda.quilos = cursor.getString(cursor.getColumnIndex(TabelaVenda.CAMPO_QUILOS));
        venda.idTipocacau = cursor.getLong(cursor.getColumnIndex(TabelaVenda.CAMPO_ID_TIPOCACAU));

        int posTipoCacau = cursor.getColumnIndex(TabelaVenda.CAMPO_TIPOCACAU);
        if (posTipoCacau != -1) {
            venda.tipoCacau = cursor.getString(posTipoCacau);

        }

        return venda;
    }

}
