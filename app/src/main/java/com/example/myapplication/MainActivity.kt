package com.example.myapplication

import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Validators.not
import android.util.Log.d
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView


class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var clearButton: Button
    private lateinit var saveButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var eMailNotCorrect: ImageView
    private lateinit var ageNotCorrect: ImageView
    private lateinit var lNameNotCorrect: ImageView
    private lateinit var fNameNotCorrect: ImageView
    private lateinit var eMailCorrect: ImageView
    private lateinit var fNameCorrect: ImageView
    private lateinit var lNameCorrect: ImageView
    private lateinit var ageCorrect: ImageView
    private lateinit var userCorrect: ImageView
    private lateinit var userNotCorrect: ImageView

    private var incorrectCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        animate()

        saveButton.setOnClickListener {

            userNameCheck()
            eMailCheck()
            ageCheck()
            nameCheck()
            lastNameCheck()
            generalCheck()

        }

        clearButton.setOnLongClickListener {
            clear()
            Toast.makeText(this, "ველები გასუფთავდა", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun init() {

        textView = findViewById(R.id.textView)
        clearButton = findViewById(R.id.clearButton)
        saveButton = findViewById(R.id.saveButton)
        ageEditText = findViewById(R.id.ageEditText)
        emailEditText = findViewById(R.id.emailEditText)
        usernameEditText = findViewById(R.id.usernameEditText)
        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        eMailNotCorrect = findViewById(R.id.eMailNotCorrect)
        lNameNotCorrect = findViewById(R.id.lNameNotCorrect)
        fNameNotCorrect = findViewById(R.id.fNameNotCorrect)
        fNameCorrect = findViewById(R.id.fNameCorrect)
        eMailCorrect = findViewById(R.id.eMailCorrect)
        eMailNotCorrect = findViewById(R.id.eMailNotCorrect)
        lNameCorrect = findViewById(R.id.lNameCorrect)
        userCorrect = findViewById(R.id.userCorrect)
        ageCorrect = findViewById(R.id.ageCorrect)
        ageNotCorrect = findViewById(R.id.ageNotCorrect)
        userNotCorrect = findViewById(R.id.userNotCorrect)
    }

    private fun clear() {

        val colorVal = ContextCompat.getColor(this, R.color.gray)

        usernameEditText.setBackgroundResource(R.drawable.input_shape)
        emailEditText.setBackgroundResource(R.drawable.input_shape)
        firstNameEditText.setBackgroundResource(R.drawable.input_shape)
        lastNameEditText.setBackgroundResource(R.drawable.input_shape)
        ageEditText.setBackgroundResource(R.drawable.input_shape)

        usernameEditText.setHintTextColor(colorVal)
        emailEditText.setHintTextColor(colorVal)
        firstNameEditText.setHintTextColor(colorVal)
        lastNameEditText.setHintTextColor(colorVal)
        ageEditText.setHintTextColor(colorVal)

        userCorrect.visibility = View.INVISIBLE
        userNotCorrect.visibility = View.INVISIBLE
        eMailNotCorrect.visibility = View.INVISIBLE
        eMailCorrect.visibility = View.INVISIBLE
        fNameCorrect.visibility = View.INVISIBLE
        fNameNotCorrect.visibility = View.INVISIBLE
        lNameCorrect.visibility = View.INVISIBLE
        lNameNotCorrect.visibility = View.INVISIBLE
        ageCorrect.visibility = View.INVISIBLE
        ageNotCorrect.visibility = View.INVISIBLE

        usernameEditText.text.clear()
        emailEditText.text.clear()
        firstNameEditText.text.clear()
        lastNameEditText.text.clear()
        ageEditText.text.clear()

    }


    private fun userNameCheck() {

        if (usernameEditText.length() >= 10) {

            userNotCorrect.visibility = View.INVISIBLE
            userCorrect.visibility = View.INVISIBLE

            userCorrect.visibility = View.VISIBLE
            usernameEditText.setBackgroundResource(R.drawable.input_correct_shape)
        } else {

            userNotCorrect.visibility = View.INVISIBLE
            userCorrect.visibility = View.INVISIBLE

            userNotCorrect.visibility = View.VISIBLE
            usernameEditText.setBackgroundResource(R.drawable.input_incorrect_shape)
            incorrectCount += 1
            val shake = AnimationUtils.loadAnimation(this, R.anim.shake)
            usernameEditText.startAnimation(shake)
            userNotCorrect.startAnimation(shake)



        }
    }

    private fun nameCheck() {

        val shake = AnimationUtils.loadAnimation(this, R.anim.shake)

        if (firstNameEditText.text.isNotEmpty()) {

            fNameNotCorrect.visibility = View.INVISIBLE
            fNameCorrect.visibility = View.INVISIBLE

            fNameCorrect.visibility = View.VISIBLE
            firstNameEditText.setBackgroundResource(R.drawable.input_correct_shape)

        } else {
            fNameNotCorrect.visibility = View.INVISIBLE
            fNameCorrect.visibility = View.INVISIBLE

            fNameNotCorrect.visibility = View.VISIBLE

            firstNameEditText.startAnimation(shake)
            fNameNotCorrect.startAnimation(shake)

            firstNameEditText.setBackgroundResource(R.drawable.input_incorrect_shape)
            incorrectCount += 1

        }

    }

    private fun lastNameCheck() {

        val shake = AnimationUtils.loadAnimation(this, R.anim.shake)

        if (lastNameEditText.text.isNotEmpty()) {

            lNameNotCorrect.visibility = View.INVISIBLE
            lNameCorrect.visibility = View.INVISIBLE

            lNameCorrect.visibility = View.VISIBLE
            lastNameEditText.setBackgroundResource(R.drawable.input_correct_shape)

        } else {
            lNameNotCorrect.visibility = View.INVISIBLE
            lNameCorrect.visibility = View.INVISIBLE

            lNameNotCorrect.visibility = View.VISIBLE

            lastNameEditText.startAnimation(shake)
            lNameNotCorrect.startAnimation(shake)

            lastNameEditText.setBackgroundResource(R.drawable.input_incorrect_shape)
            incorrectCount += 1
        }


    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun eMailCheck() {
        if (emailEditText.text.isNotEmpty() && isEmailValid(emailEditText.text.toString())) {

            eMailNotCorrect.visibility = View.INVISIBLE
            eMailCorrect.visibility = View.INVISIBLE

            eMailCorrect.visibility = View.VISIBLE
            emailEditText.setBackgroundResource(R.drawable.input_correct_shape)

        } else {
            eMailNotCorrect.visibility = View.INVISIBLE
            eMailCorrect.visibility = View.INVISIBLE

            eMailNotCorrect.visibility = View.VISIBLE
            emailEditText.setBackgroundResource(R.drawable.input_incorrect_shape)
            incorrectCount += 1
            val shake = AnimationUtils.loadAnimation(this, R.anim.shake)
            emailEditText.startAnimation(shake)
            eMailNotCorrect.startAnimation(shake)
        }
    }

    private fun ageCheck() {

        if (ageEditText.text.isNotEmpty() &&
            ageEditText.text.toString().toInt() >= 0 && ageEditText.text.toString().toInt() < 120) {

            ageCorrect.visibility = View.INVISIBLE
            ageNotCorrect.visibility = View.INVISIBLE

            ageCorrect.visibility = View.VISIBLE
            ageEditText.setBackgroundResource(R.drawable.input_correct_shape)

        } else {
            ageCorrect.visibility = View.INVISIBLE
            ageNotCorrect.visibility = View.INVISIBLE

            ageNotCorrect.visibility = View.VISIBLE
            ageNotCorrect.tooltipText = "user must contain 10 characters or more"
            incorrectCount += 1
            ageEditText.setBackgroundResource(R.drawable.input_incorrect_shape)

            val shake = AnimationUtils.loadAnimation(this, R.anim.shake)
            ageEditText.startAnimation(shake)
            ageNotCorrect.startAnimation(shake)
        }
    }

    private fun animate() {
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        clearButton.startAnimation(fadeIn)

        val fadeIn2 = AnimationUtils.loadAnimation(this, R.anim.fade_in_s)
        saveButton.startAnimation(fadeIn2)

        val sideToMid = AnimationUtils.loadAnimation(this, R.anim.side_to_mid)
        emailEditText.startAnimation(sideToMid)
        usernameEditText.startAnimation(sideToMid)
        firstNameEditText.startAnimation(sideToMid)
        lastNameEditText.startAnimation(sideToMid)
        ageEditText.startAnimation(sideToMid)
        textView.startAnimation(sideToMid)
    }

    private fun generalCheck() {
        if (incorrectCount >= 1) {
            Toast.makeText(this, "მონაცემები არ არის შეყვანილი სრულყოფილად", Toast.LENGTH_SHORT).show()
        }

    }

    private fun fieldsEmpty(): Boolean {
        return usernameEditText.text.isNotEmpty()
                && emailEditText.text.isNotEmpty() && firstNameEditText.text.isEmpty()
                && lastNameEditText.text.isNotEmpty() && ageEditText.text.isNotEmpty()

    }

}