package com.marbleinteractive.foodapp.data

import com.marbleinteractive.foodapp.data.FoodCategory.*

enum class FoodCategory(val value: String) {
    ALL("All"),
    ITALIAN("Italian"),
    ASIAN("Asian"),
    CHINESE("Chinese"),
    VEGETARIAN("Vegetarian")
}

fun getAllFoodCategories(): List<FoodCategory>{
    return listOf(ALL, ITALIAN, ASIAN, CHINESE, VEGETARIAN)
}