package com.example.myapplication

import kotlinx.android.synthetic.main.activity_main.view.*

data class Todo (
    val title: String,
    var isChecked: Boolean = false,

    val value: Double,
    val to: String,
    val from: String
)