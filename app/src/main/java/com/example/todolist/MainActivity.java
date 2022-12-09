package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnWorkItemClickListener{
    ArrayList<Work> worksData;
    RecyclerView recyclerView;
    WorkAdapter workAdapter;
    FloatingActionButton fbaAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListener();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        workAdapter.notifyDataSetChanged();
    }

    private void init(){
        recyclerView=findViewById(R.id.rcv);
        worksData=WorkUtils.getInstance().getAll();
        fbaAdd=findViewById(R.id.fab_add);
        workAdapter=new WorkAdapter(worksData,this);
        recyclerView.setAdapter(workAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void addListener(){
        fbaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddActivity();
            }
        });
    }
    private void goToAddActivity(){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(int id) {
        WorkUtils.getInstance().deleteWork(id);
        workAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_time:
                break;
        }
        return true;
    }
}