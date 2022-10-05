package com.example.testapp.data

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Repository(val itemDao: ItemDao) {
//    fun f() {
//        val calendar = Calendar.getInstance();
//        val today = calendar.getTime();
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        val tomorrow = calendar.getTime();
//        val sdf = SimpleDateFormat("yyyy-M-d")
//        val todayAsString = sdf.format(today);
//        val tomorrowAsString = sdf.format(tomorrow);
//        Log.d("lalala", "today date is $todayAsString")
//        Log.d("lalala", "tomorrow date is $tomorrowAsString")
//    }
//
//    init {
//        f()
//    }

    suspend fun update(list: ArrayList<Item>) {
        itemDao.deleteAll()
        Log.d("lalala", "delete all items")
        for (item in list) {
            itemDao.insert(item)
        }
        Log.d("lalala", "update all items")
    }

    fun mapToReadyApi(itemToday: ItemJson, itemTomorrow: ItemJson): Item {
        return Item(
            Cur_Abbreviation = itemToday.Cur_Abbreviation,
            Cur_Name = itemToday.Cur_Name,
            Cur_OfficialRateToday = itemToday.Cur_OfficialRate,
            Cur_OfficialRateTomorrow = itemTomorrow.Cur_OfficialRate,
            Cur_Scale = itemToday.Cur_Scale,
            Visibility = false,
            Date = itemToday.Date,
            Cur_ID = itemToday.Cur_ID
        )
    }

//    fun mapToReady(itemDao: Item, itemApi: Item): Item {
//        return Item(
//            Cur_Abbreviation = itemApi.Cur_Abbreviation,
//            Cur_Name = itemApi.Cur_Name,
//            Cur_OfficialRateToday = itemApi.Cur_OfficialRateToday,
//            Cur_OfficialRateTomorrow = itemApi.Cur_OfficialRateTomorrow,
//            Cur_Scale = itemApi.Cur_Scale,
//            Visibility = itemDao.Visibility,
//            Date = itemApi.Date,
//            Cur_ID = itemApi.Cur_ID
//        )
//    }

    fun getReady(listDao: ArrayList<Item>, listApi: ArrayList<Item>) {
        for (item in listApi) {
            val dao = listDao.single { it.Cur_Abbreviation == item.Cur_Abbreviation }
            dao.Cur_OfficialRateToday = item.Cur_OfficialRateToday
            dao.Cur_OfficialRateTomorrow = item.Cur_OfficialRateTomorrow
        }
    }

    fun ready(listDao: ArrayList<Item>, listApi: ArrayList<Item>) {
        for (item in listDao) {
            listApi.single { it.Cur_Abbreviation == item.Cur_Abbreviation }.Visibility =
                item.Visibility
        }
    }

    suspend fun getReadyItems(): ArrayList<Item> {
        val d = itemDao.getItems() as ArrayList<Item>
        val a = getItemsApi()
        if (d.size == a.size) {
            getReady(d, a)
            return d
        } else {
            ready(d, a)
            return a
        }
    }

    suspend fun getItemsApi(): ArrayList<Item> {
//        val calendar = Calendar.getInstance();
//        val today = calendar.getTime();
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        val tomorrow = calendar.getTime();
//        val sdf = SimpleDateFormat("yyyy-M-d")
//        val todayAsString = sdf.format(today);
        val todayAsString = "2022-10-3";
//        val tomorrowAsString = sdf.format(tomorrow);
        val tomorrowAsString = "2022-10-4";
//        Log.d("lalala", "today date is $todayAsString")
//        Log.d("lalala", "tomorrow date is $tomorrowAsString")
        val l = RetrofitInstsnse.API_SERVICES.getItemsToday(todayAsString)
        val t = RetrofitInstsnse.API_SERVICES.getItemsTomorrow(tomorrowAsString)
        val r = l.zip(t)
        return r.map { mapToReadyApi(it.first, it.second) } as ArrayList<Item>
    }


}