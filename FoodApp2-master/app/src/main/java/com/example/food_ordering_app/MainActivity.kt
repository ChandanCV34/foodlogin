package com.example.food_ordering_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      val button=findViewById<Button>(R.id.button)
        val email=findViewById<TextInputLayout>(R.id.userEmail)
        val password=findViewById<TextInputLayout>(R.id.userPassword)
        val errorMessage=findViewById<TextView>(R.id.errorView)
        val id=findViewById<TextInputLayout>(R.id.confirmPassword)
        var users= mutableListOf<User>()

        val intent = Intent(this, User_Login::class.java)

        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                FoodDatabase.getDatabase(this@MainActivity).UserDao().insert(
                    User(
                        email = email.editText?.text.toString(),
                        password = password.editText?.text.toString()
                    )
                )
            }
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {

                    if (email.isEmpty() || password.isEmpty()) {
                        errorMessage.text = "Required Fields ARE Mandatory"
                    } else {
                        errorMessage.text = "Succesfulley register"
                        startActivity(intent)
                    }
                }
            }

        }

    }
}