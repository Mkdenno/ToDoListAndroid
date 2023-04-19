package com.example.doit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter :TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoAdapter = TodoAdapter(mutableListOf())
        binding.apply {

            rvdoit.adapter = todoAdapter
            rvdoit.layoutManager =LinearLayoutManager(this@MainActivity)

            btnaddToDo.setOnClickListener { 
                val todoTitle = edtodoTitle.text.toString()
                if(todoTitle.isNotEmpty()){
                    val todo = Todo(todoTitle)
                    todoAdapter.addTodo(todo)
                    edtodoTitle.text.clear()
                }
            }
            btnDeleteDone.setOnClickListener {
                todoAdapter.deleteDone()
            }
        }

    }
}