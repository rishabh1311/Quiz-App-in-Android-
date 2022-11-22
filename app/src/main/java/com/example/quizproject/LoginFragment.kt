package com.example.quizproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.quizproject.userLoginAndSignUp.UserDatabase
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {
    lateinit var database: UserDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        database = Room.databaseBuilder(requireContext(), UserDatabase::class.java, "user_database").allowMainThreadQueries().build()

        val signupbtn = view.findViewById<Button>(R.id.signupbtnloginscreen)

        val username = view.findViewById<EditText>(R.id.loginscreenusernameedittext)
        val password = view.findViewById<EditText>(R.id.loginscrnpassedittext)
        var passwordfromdb = ""

        view.loginbtnloginscreen.setOnClickListener {
            passwordfromdb = database.userDao().checkpassword(username.text.toString())
            if(password.text.toString() == passwordfromdb){
                //login user
                Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_LONG).show()
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(requireContext(), "Wrong Credentials!", Toast.LENGTH_LONG).show()
            }
        }

        signupbtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        return view
    }

}