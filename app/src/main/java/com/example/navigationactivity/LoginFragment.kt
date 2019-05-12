package com.example.navigationactivity


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment() {
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        view.sign_in_button.setOnClickListener{signIn()}
        view.sign_up_button.setOnClickListener{signUp()}
        return view
    }

    private fun signIn(){
        val email = email_input.text.toString()
        val password = password_input.text.toString()
        if (email.isNotBlank() && password.isNotBlank()){
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    task ->
                run {
                    if (task.isSuccessful) {
                        Toast.makeText(activity, getString(R.string.success_login_message), Toast.LENGTH_SHORT).show()
                    } else {
                        val error = task.exception
                        Toast.makeText(activity, error?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }else{
            Toast.makeText(activity, getString(R.string.empty_input), Toast.LENGTH_SHORT).show()
        }

    }

    private fun signUp() {
        val email = email_input.text.toString()
        val password = password_input.text.toString()
        if(email.isNotBlank() && password.isNotBlank()){
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    task ->
                run {
                    if (task.isSuccessful) {
                        Toast.makeText(activity, getString(R.string.success_reg_message), Toast.LENGTH_SHORT).show()
                    } else {
                        val error = task.exception
                        Toast.makeText(activity, error?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }else{
            Toast.makeText(activity, getString(R.string.empty_input), Toast.LENGTH_SHORT).show()
        }
    }


}
