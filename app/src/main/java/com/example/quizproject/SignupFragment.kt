package com.example.quizproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.quizproject.userLoginAndSignUp.User
import com.example.quizproject.userLoginAndSignUp.UserDatabase
import com.example.quizproject.userLoginAndSignUp.UserViewModel
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.view.*

class SignupFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel

    lateinit var database: UserDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_signup, container, false)

        database = Room.databaseBuilder(requireContext(), UserDatabase::class.java, "user_database").allowMainThreadQueries().build()

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        val loginbtn = view.findViewById<Button>(R.id.loginbtnsignupscreen)

        view.signupbtnsignupscreen.setOnClickListener {
            val fullname = signupscreenfullnameedittext.text.toString()
            val contactno = signupscrncontactnoedittext.text.toString()
            val username = signupscrnusernameedittext.text.toString()
            val password = signupscrnpassedittext.text.toString()

            var usernameexisting = 0

            usernameexisting = database.userDao().existingusername(username)

            if(inputcheck(fullname, contactno, username, password)){

                if(usernameexisting > 0){
                    Toast.makeText(requireContext(), "Username already exists", Toast.LENGTH_LONG).show()
                }else {
                    //if fields are filled then create user
                    val user = User(0, username, password, fullname, contactno)

                    mUserViewModel.addUser(user)
                    Toast.makeText(
                        requireContext(),
                        "User Successfully Registered!",
                        Toast.LENGTH_LONG
                    ).show()
                    //navigate back to login screen after registering new user
                    findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                }

            }else{
                Toast.makeText(requireContext(), "Required Fields are Empty!", Toast.LENGTH_LONG).show()
            }
        }

        loginbtn.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        return view
    }

    private fun inputcheck(fullname: String, contactno: String, username: String, password: String): Boolean{
        return !(TextUtils.isEmpty(fullname) || TextUtils.isEmpty(contactno) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
    }

}