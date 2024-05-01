package com.ernawati.typesofshoes.di

import com.ernawati.typesofshoes.data.ShoesRepository

object Injection {
    fun provideRepository(): ShoesRepository {
        return ShoesRepository.getInstance()
    }
}