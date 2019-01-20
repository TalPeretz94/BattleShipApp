package com.example.battleshipproject.pacificbattleship;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class TileAdapter extends BaseAdapter {


    private Context mContext;
    private Board mBoard;

    public TileAdapter(Context context, Board board) {
        mBoard = board;
        mContext = context;
    }


    @Override
    public int getCount() {
        return mBoard.getbSize() * mBoard.getbSize();
    }

    @Override
    public Object getItem(int position) {
        return mBoard.getTile(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TileView tileView;
        if (convertView == null) {
            Log.e("Tile Adapter", "Not RECYCLED");
            tileView = new TileView(mContext);

        } else {
            tileView = (TileView) convertView;
            Log.e("Tile Adapter", "RECYCLED-- YAY!!!!!");
        }

        tileView.setStatus(mBoard.getTile(position).getStatus(), mBoard.isInvisable(),mBoard.getMatrixOfDirections()[position%mBoard.getbSize()][position/mBoard.getbSize()]);

        return tileView;
    }
}

