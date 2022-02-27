package com.example.myapplication

import android.content.res.AssetManager
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_todo.view.*
import java.io.InputStream
import kotlin.coroutines.coroutineContext
import com.example.myapplication.MyApplication.Companion.globalUnitConverter

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curToDo = todos[position]
        holder.itemView.apply{
            val value = curToDo.value
            val to = curToDo.to
            val from = curToDo.from
            val converted = globalUnitConverter.convert(value, from, to)
            val convertedFormatted = String.format("%.3f", converted)
            val resultingText = "$value $from converted to $to: $convertedFormatted"

            tvTodoTitle.text = resultingText.toString()
            cdDone.isChecked = curToDo.isChecked
            toggleStrikeThrough(tvTodoTitle, curToDo.isChecked)
            cdDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curToDo.isChecked = !curToDo.isChecked
            }
        }
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return todos.size
    }

}