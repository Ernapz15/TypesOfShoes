package com.ernawati.typesofshoes.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ernawati.typesofshoes.data.ShoesRepository
import com.ernawati.typesofshoes.ui.screen.cart.CartViewModel
import com.ernawati.typesofshoes.ui.screen.detail.DetailShoesViewModel
import com.ernawati.typesofshoes.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ShoesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailShoesViewModel::class.java)) {
            return DetailShoesViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}