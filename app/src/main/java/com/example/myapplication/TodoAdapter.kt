package com.example.myapplication

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R.*
import kotlinx.android.synthetic.main.item_todo.view.*



public class Converter {

    enum class Type {
        Pound,
        Kilogram,
        Ounce,
        Cups,
        Grams
    };

    private fun isWeight(type: Type) : Boolean
    {
        when(type)
        {
            Type.Pound, Type.Kilogram, Type.Ounce, Type.Grams -> {
                return true;
            }
        }
        return false;
    }


    fun convert(before: Int, fromType: Type, toType: Type) : Int {
        if(isWeight(fromType) and isWeight(toType))
        {

        }

        var result = 0;
        when(fromType) {
            Type.Cups -> {
                when(toType) {
                    Type.Cups -> {
                        result = before;
                    }
                    Type.Grams -> {
                        result = before * 120;
                    }
                }
            }
            Type.Grams -> {

            }
        }

        return result;
    }
}

class TodoAdapter (
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



            var temp = curToDo.title.toString().toInt()
            val myConverter = Converter();
            var result = myConverter.convert(temp, Converter.Type.Cups, Converter.Type.Grams)
            println(result);





            tvTodoTitle.text = result.toString()
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