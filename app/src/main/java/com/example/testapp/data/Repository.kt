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

//            val l = RetrofitInstsnse.API_SERVICES.getItemsToday()
//
//            fun mapToRedy(itemToday: Item): RedyItem {
//                return RedyItem(
//                    Abbreviation = itemToday.Cur_Abbreviation,
//                    Name = itemToday.Cur_Name,
//                    OfficialRateToday = itemToday.Cur_OfficialRate,
//                    OfficialRateTomorrow = itemToday.Cur_OfficialRate,
//                    Scale = itemToday.Cur_Scale
//                )
//            }
//
//           fun getItems(): ArrayList<RedyItem> {
//
//               return l.map { mapToRedy(it) } as ArrayList<RedyItem>
//           }

    fun getItems(): ArrayList<Item> = RetrofitInstsnse.API_SERVICES.getItemsToday()

}