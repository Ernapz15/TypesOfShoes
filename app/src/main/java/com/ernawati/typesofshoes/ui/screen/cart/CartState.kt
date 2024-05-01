package com.ernawati.typesofshoes.ui.screen.cart

import com.ernawati.typesofshoes.model.OrderShoes

data class CartState(
    val orderReward: List<OrderShoes>,
    val totalRequiredPoint: Int

)
