package com.example.boxc

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.boxc.Adapter.Dbhelper
import com.example.boxc.databinding.ActivityMain2Binding
import com.example.boxc.databinding.ActivityRegistrationPageBinding
import com.google.firebase.auth.FirebaseAuth


class RegistrationPage : AppCompatActivity() {
    lateinit var b : ActivityRegistrationPageBinding
    lateinit var  firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b =ActivityRegistrationPageBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_BoxC)
        setContentView(b.root)

        firebaseAuth = FirebaseAuth.getInstance()

        b.btn.setOnClickListener {
            if(b.name.text!!.isNotEmpty() && b.email.text!!.isNotEmpty() && b.pass.text!!.isNotEmpty()){
                 adddata()
                 createacc()
            }
            else{
                Toast.makeText(this, "Please fill all the entries", Toast.LENGTH_SHORT).show()
            }
        }






        b.already.setOnClickListener {
            startActivity(Intent(this , LoginPage::class.java))

        }
    }
    private fun createacc(){
        b.progressBar.visibility = View.VISIBLE
        var name = b.name.text.toString()
        var email = b.email.text.toString()
        var pass  = b.pass.text.toString()



        if(name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()){
            firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){
                if (it.isSuccessful){
                    b.progressBar.visibility = View.GONE

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

                }
                else{
                    b.progressBar.visibility = View.GONE

                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                }
            }
        }
        else Toast.makeText(this, "Please fill all the details ", Toast.LENGTH_SHORT).show()


    }
    private fun adddata() {

        val db = Dbhelper(this, null)

        val name = b.name.text.toString()
        val email = b.email.text.toString()
        val pass = b.pass.text.toString()

        db.adddata(name, email, pass)

        val sharedp: SharedPreferences = getSharedPreferences("hello", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedp.edit()
        editor.apply {

            putString("key1", "1")
            putString("key2" ,b.name.text.toString() )


        }.apply()



    }
}