package com.ernawati.typesofshoes.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ernawati.typesofshoes.data.ShoesRepository
import com.ernawati.typesofshoes.model.OrderShoes
import com.ernawati.typesofshoes.model.Shoes
import com.ernawati.typesofshoes.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailShoesViewModel(
    private val repository: ShoesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderShoes>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderShoes>>
        get() = _uiState

    fun getRewardById(rewardId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderRewardById(rewardId))
        }
    }

    fun addToCart(reward: Shoes, count: Int) {
        viewModelScope.launch {
            repository.updateOrderReward(reward.id, count)
        }
    }
}