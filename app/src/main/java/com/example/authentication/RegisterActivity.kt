package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val registerButton: Button = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            handleRegister()
        }
    }

    private fun handleRegister() {
        val nameInput: EditText = findViewById(R.id.nameInput)
        val emailInput: EditText = findViewById(R.id.emailInput)
        val passwordInput: EditText = findViewById(R.id.passwordInput)

        val email: String = emailInput.text.toString()
        val password: String = passwordInput.text.toString()

        try {
            if (nameInput.text.isEmpty() || emailInput.text.isEmpty() || passwordInput.text.isEmpty()) {
                throw Exception("Preencha todos os dados")
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Erro durante o cadastro", Toast.LENGTH_SHORT).show()
                    }
                }

        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}