package com.test.todoappjava.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.test.todoappjava.R;
import com.test.todoappjava.adapter.TodoListAdapter;
import com.test.todoappjava.model.TodoData;
import com.test.todoappjava.viewmodel.ToDoViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TodoListAdapter mTodoListAdapter;
    private ArrayList<TodoData> mTodoList;
    private AppCompatTextView mTextViewEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.rv_todo);
        mTextViewEmpty = findViewById(R.id.tv_empty);

        setSupportActionBar(toolbar);
        ToDoViewModel mViewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);

        mTodoList = new ArrayList<>();
        mTodoListAdapter = new TodoListAdapter(mTodoList, mViewModel);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(mTodoListAdapter);

        LiveData<List<TodoData>> allTodo = mViewModel.getAllTodo();
        allTodo.observe(this, new Observer<List<TodoData>>() {
            @Override
            public void onChanged(List<TodoData> todoData) {
                mTodoList.clear();
                mTodoList.addAll(todoData);
                if (mTodoList.isEmpty()) {
                    mTextViewEmpty.setVisibility(View.VISIBLE);
                } else {
                    mTextViewEmpty.setVisibility(View.GONE);
                }
                mTodoListAdapter.notifyDataSetChanged();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddToDoActivity.class);
                startActivity(intent);
            }
        });
    }





    }
