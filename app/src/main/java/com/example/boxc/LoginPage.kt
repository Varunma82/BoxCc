package com.example.boxc

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.boxc.Adapter.Dbhelper
import com.example.boxc.databinding.ActivityMain2Binding
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {
    lateinit var b : ActivityMain2Binding
    lateinit var  firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(b.root)

        firebaseAuth = FirebaseAuth.getInstance()

        b.btn.setOnClickListener {
            login()
        }
        b.already.setOnClickListener {
            startActivity(Intent(this , RegistrationPage::class.java))
        }


    }
    private fun login(){

        var email = b.email.text.toString()
        var pass  = b.pass.text.toString()


        if(email.isNotEmpty()  && pass.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this){
                if (it.isSuccessful){
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else
                    Toast.makeText(this, "Incorrect email and password", Toast.LENGTH_SHORT).show()
            }
        }
        else Toast.makeText(this, "Please enter credentials  ", Toast.LENGTH_SHORT).show()
    }
}