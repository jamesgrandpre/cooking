package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
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
    }
}