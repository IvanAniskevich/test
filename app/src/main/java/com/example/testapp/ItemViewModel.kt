package com.example.testapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.Item
import com.example.testapp.data.RedyItem
import com.example.testapp.data.Repository
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ItemViewModel: ViewModel() {
val i1 = Item("aaaa", 1, "dsj", 11.11, 2, "sodh", true)
val i2 = Item("bbbb", 2, "dsj", 22.22, 2, "sodh", true)
val i3 = Item("cccc", 3, "dsj", 33.33, 2, "sodh", true)


    val i = RedyItem("cx", "kjxvh", 12.1, 10.0, 1)
    val l  = arrayListOf (i1, i2, i3)

    private val repository = Repository()
    private var _listOfItem = MutableLiveData<ArrayList<Item>>()
    val listOfItem: LiveData<ArrayList<Item>>  = _listOfItem

    fun getItems() {
        viewModelScope.launch {
          try {
              _listOfItem.value = repository.getItems()
          }
          catch (e: Exception){
              _listOfItem.value = l
          }
        }
    }

    fun visabilityOn(item: Item) {
        _listOfItem.value!!.single {it == item}.Visibility = true
    }

    fun visabilityOff(item: Item) {
        _listOfItem.value!!.single {it == item}.Visibility = false
    }

    fun muveItem(initial: Int, target: Int){
        Collections.swap(_listOfItem.value, initial, target)
    }
}