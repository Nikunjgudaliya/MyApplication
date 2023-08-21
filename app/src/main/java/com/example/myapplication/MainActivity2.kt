package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity2 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        val editName = findViewById<EditText>(R.id.editText)
//        val editEmail = findViewById<EditText>(R.id.editText2)
        val btnSend = findViewById<Button>(R.id.button123)

        auth = FirebaseAuth.getInstance()

        val previous = findViewById<TextView>(R.id.login1)
        previous.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

        btnSend.setOnClickListener {
            signupUser()

//            val bundle = Bundle()
//            bundle.putString("Name", editName.text.toString())
//            bundle.putString("Email", editEmail.text.toString())

//            val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            editor.putBoolean("IS_LOGGED_IN", true)
//            editor.putString("Name", editName.text.toString())
//            editor.putString("Email", editEmail.text.toString())
//            editor.apply()

//            val intent12 = Intent(this, MainPageActivity::class.java)
//           intent12.putExtras(bundle)
//            startActivity(intent12)
        }
    }

    private fun signupUser() {

        val editName = findViewById<EditText>(R.id.editText)
        val editEmail = findViewById<EditText>(R.id.editText2)
        val editPass = findViewById<EditText>(R.id.editText3)
        val editNo = findViewById<EditText>(R.id.editText4)

        val name = editName.text.toString()
        val email = editEmail.text.toString()
        val password = editPass.text.toString()
        val no = editNo.text.toString()


        if (name.isBlank() || email.isBlank() || password.isBlank() || no.isBlank()) {
            Toast.makeText(this, "Enter data", Toast.LENGTH_SHORT).show()
        }
//        else if(password != no){
//            Toast.makeText(this, "Enter Correct password", Toast.LENGTH_SHORT).show()
//        }
        else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val storeData = user(name, email, no)

                        database = FirebaseDatabase.getInstance().getReference("User")
                        database.child(name).setValue(storeData)
                            .addOnCompleteListener {
                                Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show()

                                Handler(Looper.getMainLooper()).postDelayed({
                                    auth.signOut()
                                        val intent = Intent(this, MainActivity::class.java)
                                        startActivity(intent)
                                }, 3000)
                            }
                            .addOnFailureListener {
                                // show message to user when error occurs while saving data
                                Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Error Registering", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
