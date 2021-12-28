package com.example.mod_kotlin_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.naver.maps.map.overlay.InfoWindow;

class PointAdapter1 extends InfoWindow.DefaultViewAdapter
{
    private final Context mContext;
    private final ViewGroup mParent;

    public PointAdapter1(@NonNull Context context, ViewGroup parent)
    {
        super(context);
        mContext = context;
        mParent = parent;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    protected View getContentView(@NonNull InfoWindow infoWindow1)
    {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_point, mParent, false);

        TextView txtTitle = view.findViewById(R.id.txttitle);
        ImageView imagePoint = view.findViewById(R.id.imagepoint);
        TextView txtAddr = view.findViewById(R.id.txtaddr);
        TextView txtTel = view.findViewById(R.id.txttel);

        txtTitle.setText("교수회관 드론 대여");
        imagePoint.setImageResource(R.drawable.kyosu);
        txtAddr.setText("충청남도 천안시 동남구 안서동\n(도로명) 백석대학로1 교수회관홀 201호");
        txtTel.setText("041-550-9235");

        return view;
    }
}
