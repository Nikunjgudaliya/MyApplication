package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sign1.setOnClickListener{
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        }

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is logged in, go to the main page
            startActivity(Intent(this, MainPageActivity::class.java))
            finish()
        }
        else{
            binding.btnsend.setOnClickListener{
                loginAndSaveData()
//            val sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            editor.putBoolean("IS_LOGGED_IN", true)
////          editor.putString("Name", editName.getText().toString())
////          editor.putString("Email", editEmail.getText().toString())
//            editor.apply()
            }
        }
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    private fun loginAndSaveData() {

        val editName = binding.editTextText.text.toString()
        val editPass = binding.editTextText2.text.toString()

        if (editName.isBlank() || editPass.isBlank()) {
            Toast.makeText(this, "Enter data", Toast.LENGTH_SHORT).show()
        }
        else {
            auth.signInWithEmailAndPassword(editName, editPass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //If sign in success, do this
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainPageActivity::class.java))
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Error Login", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}



