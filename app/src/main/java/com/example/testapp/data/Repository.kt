package com.example.testapp.data

import androidx.lifecycle.asLiveData


class Repository(val itemDao: ItemDao) {

    suspend fun update(list: ArrayList<Item>){
        itemDao.deleteAll()
        for (item in list)
        itemDao.insert(item)
    }
            fun mapToRedyApi(itemToday: ItemJson, itemTomorrow: ItemJson): Item {
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

    private fun mapToRedy(itemDao: Item, itemApi: Item): Item {
        return Item(
            Cur_Abbreviation = itemApi.Cur_Abbreviation,
            Cur_Name = itemApi.Cur_Name,
            Cur_OfficialRateToday = itemApi.Cur_OfficialRateToday,
            Cur_OfficialRateTomorrow = itemApi.Cur_OfficialRateTomorrow,
            Cur_Scale = itemApi.Cur_Scale,
            Visibility = itemDao.Visibility,
            Date = itemApi.Date,
            Cur_ID = itemApi.Cur_ID
        )
    }
    suspend fun getRedyItems():ArrayList<Item>{
        return itemDao.getItems().asLiveData().value as ArrayList<Item>
//        val d = itemDao.getItems().asLiveData().value
//            val l = getItemsApi()
//            val r = d?.zip(l)
//            return r?.map { mapToRedy(it.first, it.second) } as ArrayList<Item>
    }
          suspend fun getItemsApi(): ArrayList<Item> {
               val l = RetrofitInstsnse.API_SERVICES.getItemsToday()
               val t = RetrofitInstsnse.API_SERVICES.getItemsTomorrow()
              val r = l.zip(t)
               return r.map { mapToRedyApi(it.first, it.second) } as ArrayList<Item>
           }


}