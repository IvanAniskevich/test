package com.example.testapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database (entities = [Item::class], version = 1, exportSchema = false )
abstract class ItemDatabase: RoomDatabase() {
abstract fun getItemDao(): ItemDao

    private class ItemDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.getItemDao())
                }
            }
        }

        suspend fun populateDatabase(itemDao: ItemDao) {
            // Delete all content here.
            itemDao.deleteAll()

            // Add sample words.
            var word = Item("USD",
                1,
                "usd",
                1.11,
                2.22,
                1,
                "12.12.22",
                true)
            itemDao.insert(word)
            word = Item("EUR",
                1,
                "eur",
                1.11,
                2.22,
                1,
                "12.12.22",
                true)
            itemDao.insert(word)

            // TODO: Add your own words!
        }
    }

    companion object{
        @Volatile
        private  var INSTANCE: ItemDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): ItemDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    ItemDatabase::class.java,
                    "item_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(ItemDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}