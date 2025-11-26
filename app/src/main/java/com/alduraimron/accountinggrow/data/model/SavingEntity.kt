package com.alduraimron.accountinggrow.data.model

import com.alduraimron.accountinggrow.R

data class SavingEntity(
    var id: String? = null,
    var userId: String? = null,
    var name: String? = null,
    var target: Double = 0.0,
    var amount: Double = 0.0,
    var type: SavingType,
    var isCompleted: Boolean = false,
    var imageUrl: String? = null,
    var dummyResId: Int = R.drawable.dummy_image
)

enum class SavingType {
    DAILY,
    WEEKLY,
    MONTHLY
}