package com.example.coroutinesflow_testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val adapter = ItemAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fetchData()

    }
    private fun fetchData() {
        // Здесь вызывается функция загрузки данных с использованием корутин Flow
        // Мы передаем обработчик, который будет вызываться при поступлении новых данных
        CoroutineScope(Dispatchers.Main).launch {
            loadData().collect {
                adapter.setItems(it)
            }
        }
    }
    private fun loadData(): Flow<List<String>> = flow {
        // Эмуляция задержки при загрузке данных
        delay(2000)

        // Создание списка данных
        val data = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

        // Отправка данных в Flow
        emit(data)
    }
}
//В дальнейшем будет полный рабочий проект с обработчиками ошибок и другими плюшками :)