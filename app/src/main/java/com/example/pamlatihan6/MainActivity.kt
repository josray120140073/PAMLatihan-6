package com.example.pamlatihan6

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.pamlatihan6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val users = mutableListOf<User>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val json = resources.openRawResource(R.raw.users)
            .bufferedReader().use { it.readText() }

// Log the raw JSON content
        Log.d("MainActivity", "JSON data: $json")

        val type = object : TypeToken<List<User>>() {}.type
        val userList: List<User> = Gson().fromJson(json, type)


        users.addAll(userList)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = UserAdapter(users)
    }
}