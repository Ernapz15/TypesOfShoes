package com.ernawati.typesofshoes.data

import com.ernawati.typesofshoes.model.FakeShoesDataSource
import com.ernawati.typesofshoes.model.OrderShoes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ShoesRepository {

    private val orderRewards = mutableListOf<OrderShoes>()

    init {
        if (orderRewards.isEmpty()) {
            FakeShoesDataSource.dummyRewards.forEach {
                orderRewards.add(OrderShoes(it, 0))
            }
        }
    }

    fun getAllRewards(): Flow<List<OrderShoes>> {
        return flowOf(orderRewards)
    }

    fun getOrderRewardById(rewardId: Long): OrderShoes {
        return orderRewards.first {
            it.shoes.id == rewardId
        }
    }

    fun updateOrderReward(rewardId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderRewards.indexOfFirst { it.shoes.id == rewardId }
        val result = if (index >= 0) {
            val orderReward = orderRewards[index]
            orderRewards[index] =
                orderReward.copy(shoes = orderReward.shoes, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderRewards(): Flow<List<OrderShoes>> {
        return getAllRewards()
            .map { orderShoes ->
                orderShoes.filter { orderReward ->
                    orderReward.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: ShoesRepository? = null

        fun getInstance(): ShoesRepository =
            instance ?: synchronized(this) {
                ShoesRepository().apply {
                    instance = this
                }
            }
    }
}