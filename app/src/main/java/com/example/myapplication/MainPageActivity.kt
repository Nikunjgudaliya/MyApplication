package com.example.myapplication


import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainPageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
//    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        auth = FirebaseAuth.getInstance()

//        val textView1 = findViewById<TextView>(R.id.Name12)
//        val textView2 = findViewById<TextView>(R.id.Id12)

//        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
//        val email = sharedPreferences.getString("Email", "")
//        val name = sharedPreferences.getString("Name", "")

//        textView1.text = "Name : ${name}"
//        textView2.text = "Email : ${email}"

        //--------------------------------------------------------------------
        //            val bundle = intent.extras
        //            if (bundle != null) {
        //
        //                id.text = "Id : ${bundle.getString("Name")}"
        //                name.text = "Name : ${bundle.getString("Email")}"
        //            }

        val alert1 = findViewById<Button>(R.id.Alertbox)
        alert1.setOnClickListener {
            val dialog1 = AlertDialog.Builder(this)
            dialog1.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                    finish()
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })
            val alert = dialog1.create()
            alert.setTitle("Obsidian")
            alert.show()
        }

        val snack1 = findViewById<Button>(R.id.snack)
        snack1.setOnClickListener {
            val snackBar =
                Snackbar.make(snack1, "Snack Bar.....", Snackbar.LENGTH_LONG)
            snackBar.show()
        }

        val out = findViewById<Button>(R.id.button2)

        out.setOnClickListener {
            auth.signOut()

            val show = findViewById<TextView>(R.id.textView3)
            show.text = ""

            // Navigate to the login screen
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

//            startActivity(Intent(this, MainActivity::class.java))
//            val sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            editor.putBoolean("IS_LOGGED_IN", false)
//            editor.apply()
//            finish()
            }

            val list = findViewById<Button>(R.id.listview)
            list.setOnClickListener {
                startActivity(Intent(this, listview::class.java))
            }

            val card = findViewById<Button>(R.id.Cardview)
            card.setOnClickListener {
                startActivity(Intent(this, Cardview::class.java))
            }

            val page = findViewById<Button>(R.id.pageview)
            page.setOnClickListener {
                startActivity(Intent(this, Pageview::class.java))
            }



        val fetch = findViewById<Button>(R.id.btnFetch)
        fetch.setOnClickListener {
           fetchData()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun fetchData() {

        val show = findViewById<TextView>(R.id.textView3)
        val databaseReference = FirebaseDatabase.getInstance().getReference("User")
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val userdata = StringBuilder()
                        for (userSnapshot in dataSnapshot.children) {
                            val name = userSnapshot.child("name").getValue(String::class.java)
                            val email = userSnapshot.child("email").getValue(String::class.java)
                            val no = userSnapshot.child("no").getValue(String::class.java)

                            userdata.append("Name: $name\nEmail: $email\nNumber: $no\n\n")
//                            val userDetails = "Name: $name\nEmail: $email\nNumber: $no\n"
//                            show.text = userDetails
                        }
                        show.text = userdata.toString()
                    }
                    else{
                        show.text = "No user Exists"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Error Fetching Data", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }

