package com.c1ctech.jetpackdatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var userManager: UserManager
    var age = 0
    var fname = ""
    var lname = ""
    var gender = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get reference to our userManager class
        userManager = UserManager(dataStore)

        buttonSave()

        observeData()

    }


    private fun observeData() {

        //Updates age
        userManager.userAgeFlow.asLiveData().observe(this, {
            if (it != null) {
                age = it
                tv_age.text = it.toString()
            }


        })

        //Updates firstname
        userManager.userFirstNameFlow.asLiveData().observe(this, {
            if (it != null) {
                fname = it
                tv_fname.text = it
            }


        })

        //Updates lastname
        userManager.userLastNameFlow.asLiveData().observe(this, {
            if (it != null) {
                lname = it
                tv_lname.text = it
            }


        })

        //Updates gender
        userManager.userGenderFlow.asLiveData().observe(this, {
            if (it != null) {
                gender = if (it) "Male" else "Female"
                tv_gender.text = gender
            }
        })
    }

    private fun buttonSave() {

        //Gets the user input and saves it
        btn_save.setOnClickListener {
            fname = et_fname.text.toString()
            lname = et_lname.text.toString()
            age = et_age.text.toString().toInt()
            val isMale = switch_gender.isChecked

            //Stores the values
            GlobalScope.launch {
                userManager.storeUser(age, fname, lname, isMale)
            }
        }


    }
}
