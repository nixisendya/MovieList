package com.example.lab4

import java.io.Serializable

data class MovieModel(val imageResId: Int,
                    val name: String,
                    val description: String,
                    val url: String,
                    var text: String = "") : Serializable
