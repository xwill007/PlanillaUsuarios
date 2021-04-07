package com.WillVargas


import DatePickerFragment
import android.app.DatePickerDialog.OnDateSetListener
import android.app.PendingIntent.getActivity
import android.media.ImageReader.newInstance
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.WillVargas.databinding.ActivityMainBinding
import java.util.*


private const val EMPTY = ""
private const val SPACE = " "
public var fecha = " "

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var users: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        //cleanViews()

        mainBinding.saveButton.setOnClickListener {
            Log.d("Click", "true")
            var name = mainBinding.nameTextInputEditText.text.toString()
            var date = fecha
            val email = mainBinding.emailEditText.text.toString()
            val password = mainBinding.passwordEditText.text.toString()
            val repPassword = mainBinding.repPasswordEditText.text.toString()
            val genre = if (mainBinding.femaleRadioButton.isChecked) getString(R.string.female) else getString(R.string.male)
            val city = mainBinding.spinnerPlace.selectedItem.toString()
            var hobbies = if (mainBinding.danceCheckBox.isChecked)getString(R.string.dance) else EMPTY
            hobbies = hobbies + SPACE + if (mainBinding.eatCheckBox.isChecked)getString(R.string.eat) else EMPTY
            hobbies = hobbies + SPACE + if (mainBinding.readCheckBox.isChecked)getString(R.string.read) else EMPTY
            hobbies = hobbies + SPACE + if (mainBinding.sportCheckBox.isChecked)getString(R.string.sport) else EMPTY


            if (email.isNotEmpty() and name.isNotEmpty() ) {
                if (password == repPassword) {
                    mainBinding.repPasswordTextInputLayout.error = null
                    if( fecha.isNotEmpty()){
                        saveUser( name, email, password, genre, hobbies, date, city)}
                    else Toast.makeText(this, getString(R.string.please_input_date), Toast.LENGTH_LONG).show()
                } else {
                    mainBinding.repPasswordTextInputLayout.error = getString(R.string.pasword_error)
                }
            } else Toast.makeText(this, getString(R.string.email_error), Toast.LENGTH_LONG).show()
            cleanViews()
        }
    }

    private fun saveUser(name: String, email: String, password: String, genre: String, hobbies: String, date: String, city: String ) {
        val newUser = User(name, email, password, genre, hobbies, date, city)
        //mainBinding.infoTextView.text = newUser.email+"\n"+newUser.genre+"\n"+newUser.hobbies
        users.add(newUser)
        printUserData()
    }

    private fun printUserData(){
        var info =""
        for(user in users) {
            info = info+ "\n\n"+ user.name + "\n" +user.email + "\n" + user.genre + "\n" + user.hobbies + "\n" + user.date + "\n" + user.city + "\n"
        }
        mainBinding.infoTextView.text = info
    }

    private fun cleanViews(){
        with(mainBinding){
            emailEditText.setText(EMPTY)
            passwordEditText.setText(EMPTY)
            repPasswordEditText.setText(EMPTY)
            maleRadioButton.isChecked=true
            danceCheckBox.isChecked=false
            eatCheckBox.isChecked=false
            sportCheckBox.isChecked=false
            readCheckBox.isChecked=false
            fecha = EMPTY
            nameTextInputEditText.setText(EMPTY)
        }
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

}


