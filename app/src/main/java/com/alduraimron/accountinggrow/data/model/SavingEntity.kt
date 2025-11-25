package com.alduraimron.accountinggrow.data.model

data class SavingEntity(
    var id: String? = null,
    var userId: String? = null,
    var name: String? = null,
    var target: Double = 0.0,
    var amount: Double = 0.0,
    var isCompleted: Boolean = false
)