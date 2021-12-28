package com.example.mod_kotlin_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DroneListAdapter extends RecyclerView.Adapter<DroneListAdapter.ViewHolder> {

    private final ArrayList<DroneListData> dataList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // item.xml 을 parent ViewGroup 위에 Inflate 시켜 새로운 View 를 하나 만든다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_drone_list_item,parent,false);

        // 이 View 를 바탕으로 ItemViewHolder 객체 생성
        return new ViewHolder(view);
    }

    // ViewHolder 에 각각의 항목들을 바인딩시킨다.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(dataList.get(position));

        holder.item_list.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DroneInformation.class);
            intent.putExtra("number", position);
            intent.putExtra("title", dataList.get(position).getTitle());
            intent.putExtra("content", dataList.get(position).getContent());
            intent.putExtra("price", dataList.get(position).getPrice());
            v.getContext().startActivity(intent);
            Toast.makeText(v.getContext(), "드론을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItem(DroneListData data) {
        dataList.add(data);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView1;
        private final TextView textView2;
        private final TextView textView3;
        private final ImageView imageView;
        private final LinearLayout item_list;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_list = itemView.findViewById(R.id.drone_item_list);
            textView1 = itemView.findViewById(R.id.droneName);
            textView2 = itemView.findViewById(R.id.droneWeight);
            textView3 = itemView.findViewById(R.id.dronePrice);
            imageView = itemView.findViewById(R.id.droneImg);
        }

        public void onBind(DroneListData data) {

            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            textView3.setText(data.getPrice());
            imageView.setImageResource(data.getResId());
        }
    }
}
