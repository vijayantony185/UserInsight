package com.user.insight.data

import com.google.gson.annotations.SerializedName

data class UserDetails(

    @SerializedName("users") var users: ArrayList<Users> = arrayListOf(),
    @SerializedName("total") var total: Int? = null,
    @SerializedName("skip") var skip: Int? = null,
    @SerializedName("limit") var limit: Int? = null

)

data class Hair(

    @SerializedName("color") var color: String? = null,
    @SerializedName("type") var type: String? = null

)

data class Coordinates(

    @SerializedName("lat") var lat: Double? = null,
    @SerializedName("lng") var lng: Double? = null

)

data class Address(

    @SerializedName("address") var address: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("coordinates") var coordinates: Coordinates? = Coordinates(),
    @SerializedName("postalCode") var postalCode: String? = null,
    @SerializedName("state") var state: String? = null

)

data class Bank(

    @SerializedName("cardExpire") var cardExpire: String? = null,
    @SerializedName("cardNumber") var cardNumber: String? = null,
    @SerializedName("cardType") var cardType: String? = null,
    @SerializedName("currency") var currency: String? = null,
    @SerializedName("iban") var iban: String? = null

)

data class Company(

    @SerializedName("address") var address: Address? = Address(),
    @SerializedName("department") var department: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("title") var title: String? = null

)

data class Crypto(

    @SerializedName("coin") var coin: String? = null,
    @SerializedName("wallet") var wallet: String? = null,
    @SerializedName("network") var network: String? = null

)

data class Users(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("maidenName") var maidenName: String? = null,
    @SerializedName("age") var age: Int? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("birthDate") var birthDate: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("bloodGroup") var bloodGroup: String? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("weight") var weight: Double? = null,
    @SerializedName("eyeColor") var eyeColor: String? = null,
    @SerializedName("hair") var hair: Hair? = Hair(),
    @SerializedName("domain") var domain: String? = null,
    @SerializedName("ip") var ip: String? = null,
    @SerializedName("address") var address: Address? = Address(),
    @SerializedName("macAddress") var macAddress: String? = null,
    @SerializedName("university") var university: String? = null,
    @SerializedName("bank") var bank: Bank? = Bank(),
    @SerializedName("company") var company: Company? = Company(),
    @SerializedName("ein") var ein: String? = null,
    @SerializedName("ssn") var ssn: String? = null,
    @SerializedName("userAgent") var userAgent: String? = null,
    @SerializedName("crypto") var crypto: Crypto? = Crypto()

)
