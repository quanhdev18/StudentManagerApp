package com.example.studentmanagement

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var full_name: EditText
    private lateinit var student_code: EditText
    private lateinit var add_btn: Button
    private lateinit var update_btn: Button
    private lateinit var delete_btn: Button
    private lateinit var data_list: ListView

    private val studentList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private var selectedPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        full_name = findViewById(R.id.full_name)
        student_code = findViewById(R.id.student_code)
        add_btn = findViewById(R.id.add_btn)
        update_btn = findViewById(R.id.update_btn)
        delete_btn = findViewById(R.id.delete_btn)
        data_list = findViewById(R.id.data_list)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentList)
        data_list.adapter = adapter

        add_btn.setOnClickListener {
            val name = full_name.text.toString()
            val code = student_code.text.toString()
            if (name.isNotEmpty() && code.isNotEmpty()) {
                studentList.add("$name - $code")
                adapter.notifyDataSetChanged()
                clearInput()
            }
        }

        data_list.setOnItemClickListener { _, _, position, _ ->
            val selected = studentList[position]
            val parts = selected.split(" - ")
            full_name.setText(parts[0])
            student_code.setText(parts[1])
            selectedPosition = position
        }

        update_btn.setOnClickListener {
            if (selectedPosition >= 0) {
                val name = full_name.text.toString()
                val code = student_code.text.toString()
                studentList[selectedPosition] = "$name - $code"
                adapter.notifyDataSetChanged()
                clearInput()
                selectedPosition = -1
            }
        }

        delete_btn.setOnClickListener {
            if (selectedPosition >= 0) {
                studentList.removeAt(selectedPosition)
                adapter.notifyDataSetChanged()
                clearInput()
                selectedPosition = -1
            }
        }
    }

    private fun clearInput() {
        full_name.text.clear()
        student_code.text.clear()
    }
}

