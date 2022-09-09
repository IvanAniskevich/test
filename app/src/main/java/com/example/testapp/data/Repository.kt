package com.example.testapp.data




class Repository {
//    val today: ArrayList<Item> = RetrofitInstsnse.API_SERVICES.getItemsToday()
//    val tomorrow: ArrayList<Item> = RetrofitInstsnse.API_SERVICES.getItemsTomorrow()
//    var redy = ArrayList<RedyItem>(today.size)
//    var i: Int = 0
//
//
//    fun mapToRedy(itemToday: Item, itemTomorrow: Item): RedyItem {
//        if (i < redy.size - 1) i++
//        else i = 0
//        return RedyItem(
//            Abbreviation = itemToday.Cur_Abbreviation,
//            Name = itemToday.Cur_Name,
//            OfficialRateToday = itemToday.Cur_OfficialRate,
//            OfficialRateTomorrow = itemTomorrow.Cur_OfficialRate,
//            Scale = itemToday.Cur_Scale
//        )
//    }
//
//    fun getIList(today: ArrayList<Item>, tomorrow: ArrayList<Item>): ArrayList<RedyItem> {
//        return redy.map { mapToRedy(today[i], tomorrow[i]) } as ArrayList<RedyItem>
//    }
//
//    fun getItems(): ArrayList<RedyItem> {
//        return getIList(today, tomorrow)
//    }

//попробовать через .zip


            fun mapToRedy(itemToday: ItemJson, itemTomorrow: ItemJson): Item {
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

          suspend fun getItems(): ArrayList<Item> {
               val l = RetrofitInstsnse.API_SERVICES.getItemsToday()
               val t = RetrofitInstsnse.API_SERVICES.getItemsTomorrow()
              val r = l.zip(t)
               return r.map { mapToRedy(it.first, it.second) } as ArrayList<Item>
           }

//    fun getItems(): ArrayList<Item> = RetrofitInstsnse.API_SERVICES.getItemsToday()

}