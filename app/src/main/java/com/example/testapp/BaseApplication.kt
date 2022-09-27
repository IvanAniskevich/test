package com.example.testapp

import android.app.Application
import com.example.testapp.data.ItemDatabase
import com.example.testapp.data.Repository

class BaseApplication: Application() {
    val database: ItemDatabase by lazy { ItemDatabase.getDatabase(this) }
    val repository: Repository by lazy { Repository(database.getItemDao()) }
}