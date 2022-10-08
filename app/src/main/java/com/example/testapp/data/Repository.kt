package com.example.testapp.data

import java.text.SimpleDateFormat
import java.util.*


class Repository(val itemDao: ItemDao) {

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
            Visibility = false,
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

    fun getToday(): Date {
        val calendar = Calendar.getInstance()
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
        val today = getTomorrow()
        val tomorrowAsString = sdf.format(tomorrow)
        val todayAsString = sdf.format(today)
        val l = RetrofitInstance.API_SERVICES.getItemsToday(todayAsString, "0")
        val t = RetrofitInstance.API_SERVICES.getItemsTomorrow(tomorrowAsString, "0")
        val r = l.zip(t)
        return r.map { mapToReadyApi(it.first, it.second) } as ArrayList<Item>
    }
}