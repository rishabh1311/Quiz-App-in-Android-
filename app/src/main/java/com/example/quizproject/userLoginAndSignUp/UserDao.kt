package com.example.quizproject.userLoginAndSignUp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun adduser(user: User)

    @Query("SELECT * from user_table ORDER BY userId ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT userPass from user_table WHERE userName like :username")
    fun checkpassword(username: String): String

    @Query("SELECT COUNT() from user_table WHERE userName like :username")
    fun existingusername(username: String): Int
}