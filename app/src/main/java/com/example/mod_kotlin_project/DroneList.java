package com.example.mod_kotlin_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class DroneList extends AppCompatActivity {

    private DroneListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_list);

        ImageButton back = findViewById(R.id.backBtn);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),RentalPeriod.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView_droneList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new DroneListAdapter();
        recyclerView.setAdapter(adapter);

        getData();
    } //onCreate

    @SuppressLint("NotifyDataSetChanged")
    private void getData() {
        List<String> titleList = Arrays.asList("MOD-김동규","MOD-이호현","MOD-선경은","MOD-백석");
        List<String> contentList = Arrays.asList("2.1KG","1.7KG","1.8KG","0.7KG");
        List<String> priceList = Arrays.asList("10000원/H","10000원/H","15000원/H","5000원/H");
        List<Integer> rsIdList = Arrays.asList(R.drawable.real_drone_1,R.drawable.real_drone_2
                ,R.drawable.real_drone_3,R.drawable.real_drone_4);

        for (int i=0; i<4; i++){
            DroneListData data = new DroneListData();
            data.setTitle(titleList.get(i));
            data.setContent(contentList.get(i));
            data.setPrice(priceList.get(i));
            data.setResId(rsIdList.get(i));

            // adapter 에 방금 만든 Data 객체를 추가해 넣는다
            adapter.addItem(data);
        }

        // adapter 내용의 값이 변경되었음을 알려준다. 이 함수를 사용하지 않으면 Data 가 노출되지 않음.
        adapter.notifyDataSetChanged();
    }
}