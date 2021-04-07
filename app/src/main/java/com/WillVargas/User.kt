package com.WillVargas

data class User (
    var name:String,
    var email:String,
    var password:String,
    var genre:String,
    var hobbies:String? = null,
    var date:String? = null,
    var city:String
)