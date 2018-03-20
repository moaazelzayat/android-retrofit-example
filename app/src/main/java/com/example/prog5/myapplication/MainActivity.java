package com.example.prog5.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.prog5.myapplication.adapter.EmployeeAdapter;
import com.example.prog5.myapplication.model.Employee;
import com.example.prog5.myapplication.model.EmployeeList;
import com.example.prog5.myapplication.netwoek.GetEmployeeDataService;
import com.example.prog5.myapplication.netwoek.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EmployeeAdapter adapter;
    private RecyclerView recyclerView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.getdata);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                GetEmployeeDataService service = RetrofitInstance.getRetrofitInstance().create(GetEmployeeDataService.class);


                Call<EmployeeList> call = service.getEmployeeData(123);


                //Log.wtf("URL Called", call.request().url() + "");

                call.enqueue(new Callback<EmployeeList>() {
                    @Override
                    public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                        generateEmployeeList(response.body().getEmployeeList());
                    }

                    @Override
                    public void onFailure(Call<EmployeeList> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    /*Method to generate List of employees using RecyclerView with custom adapter*/
    private void generateEmployeeList(ArrayList<Employee> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_employee_list);

        adapter = new EmployeeAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
    }

