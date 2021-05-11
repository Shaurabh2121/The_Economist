package com.example.TheEconomist.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.TheEconomist.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth= FirebaseAuth.getInstance()
        if(auth.currentUser!=null){
            val action =LoginFragmentDirections.actionGlobalUserProfileFragment()
                        findNavController().navigate(action)
        }

        btnSignIn.setOnClickListener {
            val email=etEmail.text.toString()
            val password=etPassword.text.toString()
            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                Toast.makeText(context, "email and password are required", Toast.LENGTH_SHORT).show()
            }else{
                auth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this.requireActivity()){task->
                    if (task.isSuccessful){
                        val action =LoginFragmentDirections.actionGlobalUserProfileFragment()
                        findNavController().navigate(action)
                    }else{
                        Toast.makeText(context, "email and password are not available plz sign-up", Toast.LENGTH_SHORT).show()

                    }
                }
            }

        }
        btnSignUp.setOnClickListener {
            val action =LoginFragmentDirections.actionGlobalSignUpFragment()
            findNavController().navigate(action)
        }
    }
}