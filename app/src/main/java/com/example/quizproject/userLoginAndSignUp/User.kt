package com.example.quizproject.userLoginAndSignUp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val userName: String,
    val userPass: String,
    val userFullName: String,
    val userContactNo: String
)
