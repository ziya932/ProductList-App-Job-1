package com.example.yaya.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yaya.ApiInstance
import com.example.yaya.Product
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products
    init {
        viewModelScope.launch {
            fetchProducts()
        }
    }

    private  suspend fun fetchProducts() {
        try {
            val response = ApiInstance.apiService.getProducts()
            _products.postValue(response)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}