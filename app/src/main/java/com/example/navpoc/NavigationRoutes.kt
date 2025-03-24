package com.example.navpoc

import kotlinx.serialization.Serializable

@Serializable
data object ScreenStart

@Serializable
data object ScreenOne

@Serializable
data class ScreenTwo(
    val data: DataScreenTwo
)

@Serializable
data class DataScreenTwo(
    val title: String,
    val subtitle: String,
)