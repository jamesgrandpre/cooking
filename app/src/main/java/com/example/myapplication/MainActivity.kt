package com.example.myapplication

import android.app.Application
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MyApplication: Application() {
    companion object {
        lateinit var globalUnitConverter: UnitConverter
    }
    override fun onCreate() {
        super.onCreate()
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.globalUnitConverter = UnitConverter(assets)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val todoTitle = etValue.text.toString()
            if(todoTitle.isNotEmpty()){
                val value = todoTitle.toDouble()
                val to = etTo.text.toString()
                val from = etFrom.text.toString()
                val todo = Todo(todoTitle, false, value, to, from)
                todoAdapter.addTodo(todo)
            }
        }

       btnDeleteTodo.setOnClickListener{
           todoAdapter.deleteDoneTodos()
       }

        val units = MyApplication.globalUnitConverter.getTypes()
        toSpinner.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            units)
    }
}