package com.example.TheEconomist.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.TheEconomist.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        btnRegister.setOnClickListener {
            val userName = etNameUp.text.toString()
            val email = etEmailUp.text.toString()
            val password = etPasswordUp.text.toString()
            val confirmPassword = etConfirmPasswordUp.text.toString()

            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(context, "username is required", Toast.LENGTH_SHORT)
                    .show()
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(context, "email is required", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(context, "password is required", Toast.LENGTH_SHORT)
                    .show()
            }

            if (TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(
                    context,
                    "confirm password is required",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(context, "password not match", Toast.LENGTH_SHORT).show()
            }
            registerUser(userName, email, password)
        }
    }

    private fun registerUser(userName: String, email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = auth.currentUser
                val userId = user.uid
                databaseReference =
                    FirebaseDatabase.getInstance().getReference("Users").child(userId)
                val hashmap: HashMap<String, String> = HashMap()
                hashmap.put("userId", userId)
                hashmap.put("username", userName)

                databaseReference.setValue(hashmap).addOnCompleteListener {
                    if (it.isSuccessful) {

                        val action=SignUpFragmentDirections.actionGlobalLoginFragment()
                        findNavController().navigate(action)
                    }


                }
            }
        }
    }
}