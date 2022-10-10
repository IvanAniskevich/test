package com.example.testapp

import androidx.lifecycle.*
import com.example.testapp.data.Item
import com.example.testapp.data.Repository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ItemViewModel(val repository: Repository) : ViewModel() {
    val i1 = Item("aaaa", 1, "dsj", 11.11, 11.22, 1, "sodh", true)
    val i2 = Item("bbbb", 2, "dsj", 22.11, 22.22, 1, "sodh", true)
    val i3 = Item("cccc", 3, "dsj", 33.11, 33.22, 1, "sodh", true)
    val l = arrayListOf(i1, i2, i3)

    private var _listOfItem = MutableLiveData<ArrayList<Item>>()
    val listOfItem: LiveData<ArrayList<Item>> = _listOfItem
    fun getItems() {
        viewModelScope.launch {
//            _listOfItem.value = repository.getItemsApi()
            try {
                _listOfItem.value = repository.getReadyItems()
            } catch (e: Exception) {
                _listOfItem.value = l
            }
        }
    }

    init {
        getItems()
    }

    fun visabilityOn(item: Item) {
        _listOfItem.value!!.single { it == item }.Visibility = true
    }

    fun visabilityOff(item: Item) {
        _listOfItem.value!!.single { it == item }.Visibility = false
    }

    fun muveItem(initial: Int, target: Int) {
        Collections.swap(_listOfItem.value!!, initial, target)
    }

    fun update() {
        viewModelScope.launch { _listOfItem.value?.let { repository.update(it) } }
    }
    fun getFirstDate(): String{
        val sdf = SimpleDateFormat("d.M.yy")
        val today = repository.firstDate
        return sdf.format(today)
    }
    fun getSecondDate(): String{
        val sdf = SimpleDateFormat("d.M.yy")
        val tomorrow = repository.secondDate
        return sdf.format(tomorrow)
    }
}

class ItemViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
