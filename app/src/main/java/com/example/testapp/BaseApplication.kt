package com.example.testapp

import android.app.Application
import com.example.testapp.data.ItemDatabase
import com.example.testapp.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BaseApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database: ItemDatabase by lazy { ItemDatabase.getDatabase(this, applicationScope) }
    val repository: Repository by lazy { Repository(database.getItemDao()) }
}