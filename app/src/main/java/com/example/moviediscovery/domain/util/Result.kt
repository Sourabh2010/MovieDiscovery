package com.example.moviediscovery.domain.util

sealed class Result< out T> {

    data object Idle : Result<Nothing>()  // Idle – Kuch hua hi nahi ab tak
    data object Loading : Result<Nothing>() //  Operation chal raha hai (jaise API request ja rahi hai)
    data class Success<T>(val data: T) : Result<T>() // Operation success ho gaya aur hume data mila
    data class Failure(val message:String):Result<Nothing>() //  Operation fail ho gaya, aur hume error message mila

}