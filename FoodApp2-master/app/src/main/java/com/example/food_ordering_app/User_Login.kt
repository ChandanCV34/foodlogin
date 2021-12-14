package com.example.food_ordering_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Header

class User_Login : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        val email = findViewById<TextInputLayout>(R.id.userEmail)
        val password = findViewById<TextInputLayout>(R.id.userPassword)
        val button = findViewById<Button>(R.id.button)

        val errorMessage = findViewById<TextView>(R.id.errorView)
        val registernav = findViewById<Button>(R.id.button2)

        val inputemailname = MutableLiveData<String>()

        val inputPassword = MutableLiveData<String>()


        val intent = Intent(this, MainActivity::class.java)
        val intent1 = Intent(this, MainActivity::class.java)
        apiClient = ApiClient()
        sessionManager = SessionManager(this,)

        button.setOnClickListener {
        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Unconfined) {
                apiClient.getApiService().login(
                     " ${sessionManager.fetchAuthToken()}",
                    LoginRequest("user3@gmail.com","password1")
                )
                    .enqueue(object : Callback<LoginResponse> {


                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                        /*    TODO("Not yet implemented"*/
                            val loginResponse = response.body()
                            if (loginResponse?.email != null) {
                                if(response.isSuccessful())
                                {
                                    sessionManager.saveAuthToken(loginResponse.token)
                                    startActivity(intent)
                                    Toast.makeText(this@User_Login,loginResponse.token,Toast.LENGTH_SHORT).show()
                                     val token=loginResponse.token

                                    errorMessage.text="Bearer ${sessionManager.fetchAuthToken()}"
                                }else{
                                    Toast.makeText(this@User_Login,"login",Toast.LENGTH_SHORT).show()

                                }



                            } else {

                                // Error logging in
                            }
                        }
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            // Error logging in

                          errorMessage.text="Bearer ${sessionManager.fetchAuthToken()}"

                        }

                    })
            }
        }
    }


       /* button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var user:List<User>? = FoodDatabase.getDatabase(this@User_Login).UserDao().findByUserEmailPassword(
                    email = email.editText?.text.toString(),
                    password = password.editText?.text.toString()
                )
                withContext(Dispatchers.Main) {
                    if (user != null && user.size>0) {
                        startActivity(intent)
                    }
                }
            }*/

           /* CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    if (email == null || password == null) {
                        errorMessage.text = "Required Fields ARE Mandatory"
                    }
                    *//*else if(inputemailname==email && inputPassword==password) {
                        startActivity(intent)
                    }*//*
                    else {
                        var useremail:List<User>? =   FoodDatabase.getDatabase(this@User_Login).UserDao().getUsername(
                                email = email.editText?.text.toString()
                            )

                        val userspassword:List<User> =
                            FoodDatabase.getDatabase(this@User_Login).UserDao().getpassword(
                                password = password.editText?.text.toString()
                            )
                        if (useremail != null && userspassword != null) {
                            if (useremail.get(0).email==email.toString() && userspassword.get(0).password == password.toString()) {
                                *//*inputemailname.value = null
                                inputPassword.value = null*//*
                                startActivity(intent)
                            }
                        }
                    }
                }
            }*/
        }

      /*  registernav.setOnClickListener {
            startActivity(intent1)
        }*/
    }

