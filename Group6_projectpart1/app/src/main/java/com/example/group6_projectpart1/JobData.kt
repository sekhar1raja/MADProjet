package com.example.group6_projectpart1

data class JobData(
    val Id: String = "",
    val Title: String = "",
    val Description: String = "",
    val Image: String = "",
    val FullDesc: String = ""
) {
    constructor() : this("", "", "", "","")
}