package com.example.testapp.data

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Repository(val itemDao: ItemDao) {
    val firstDate: Date = getToday()
    var secondDate: Date = getTomorrow()
    suspend fun update(list: ArrayList<Item>) {
        itemDao.deleteAll()
        for (item in list) {
            itemDao.insert(item)
        }
    }

    fun mapToReadyApi(itemToday: ItemJson, itemTomorrow: ItemJson): Item {
        return Item(
            Cur_Abbreviation = itemToday.Cur_Abbreviation,
            Cur_Name = itemToday.Cur_Name,
            Cur_OfficialRateToday = itemToday.Cur_OfficialRate,
            Cur_OfficialRateTomorrow = itemTomorrow.Cur_OfficialRate,
            Cur_Scale = itemToday.Cur_Scale,
            Visibility = true,
            Date = itemToday.Date,
            Cur_ID = itemToday.Cur_ID
        )
    }

    fun getReady(listDao: ArrayList<Item>, listApi: ArrayList<Item>) {
        for (item in listApi) {
            val dao = listDao.single { it.Cur_Abbreviation == item.Cur_Abbreviation }
            dao.Cur_OfficialRateToday = item.Cur_OfficialRateToday
            dao.Cur_OfficialRateTomorrow = item.Cur_OfficialRateTomorrow
        }
    }

//    fun ready(listDao: ArrayList<Item>, listApi: ArrayList<Item>) {
//        for (item in listDao) {
//            listApi.single { it.Cur_Abbreviation == item.Cur_Abbreviation }.Visibility =
//                item.Visibility
//        }
//    }

    suspend fun getReadyItems(): ArrayList<Item> {
        val d = itemDao.getItems() as ArrayList<Item>
        val a = getItemsApi()
        if (d.size == a.size) {
            getReady(d, a)
            return d
        } else {
//            ready(d,a)
            return a
        }
    }

    fun getToday(): Date {
        val calendar = Calendar.getInstance()
        return calendar.time
    }

    fun getYesterday(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        return calendar.time
    }

    fun getTomorrow(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        return calendar.time
    }

    suspend fun getItemsApi(): ArrayList<Item> {
        val sdf = SimpleDateFormat("yyyy-M-d")
        val tomorrow = getTomorrow()
        val today = getToday()
        val yesterday = getYesterday()
        val tomorrowAsString = sdf.format(tomorrow)
        val todayAsString = sdf.format(today)
        val yesterdayAsString = sdf.format(yesterday)
        val f = RetrofitInstance.API_SERVICES.getItems(todayAsString, "0")
        var s = RetrofitInstance.API_SERVICES.getItems(tomorrowAsString, "0")
        if (s.size == 0) {
            s = RetrofitInstance.API_SERVICES.getItems(yesterdayAsString, "0")
            secondDate = getYesterday()
        }
        val r = f.zip(s)
        return r.map { mapToReadyApi(it.first, it.second) } as ArrayList<Item>
    }
}