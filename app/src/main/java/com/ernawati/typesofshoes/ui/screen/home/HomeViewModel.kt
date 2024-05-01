package com.ernawati.typesofshoes.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ernawati.typesofshoes.data.ShoesRepository
import com.ernawati.typesofshoes.model.OrderShoes
import com.ernawati.typesofshoes.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: ShoesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderShoes>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderShoes>>>
        get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllRewards()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderShoes ->
                    _uiState.value = UiState.Success(orderShoes)
                }
        }
    }
}