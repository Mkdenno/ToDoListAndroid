package com.example.doit

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doit.databinding.ItemTodoBinding

class TodoAdapter (
    private val todos: MutableList<Todo>
        ): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
{
        class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemTodoBinding.inflate(inflater, parent, false)
                return TodoViewHolder(
                        binding
                )
        }

        fun addTodo(todo: Todo){
                todos.add(todo)
                notifyItemInserted(todos.size -1)
        }

        fun  deleteDone(){
        todos.removeAll{todo ->
                todo.isChecked
        }
                notifyDataSetChanged()
        }

        private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked:Boolean){
                if(isChecked){
                        tvTodoTitle.paintFlags =tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
                }
                else{
                        tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
                }
        }

        override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
                val curTodo=todos[position]

                holder.binding.apply {
                        tvTodoTitle.text = curTodo.title
                        cbdone.isChecked = curTodo.isChecked
                        toggleStrikeThrough(tvTodoTitle,curTodo.isChecked)
                        cbdone.setOnCheckedChangeListener{_, isChecked ->
                                toggleStrikeThrough(tvTodoTitle, isChecked)
                                curTodo.isChecked = !curTodo.isChecked
                        }
                }
        }
        override fun getItemCount(): Int {
                return todos.size
        }
}