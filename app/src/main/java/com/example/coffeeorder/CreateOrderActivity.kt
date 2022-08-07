package com.example.coffeeorder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get

class CreateOrderActivity : AppCompatActivity() {

    private lateinit var tvWelcoming: TextView
    private lateinit var tvAdditions: TextView

    private lateinit var cbMilk: CheckBox
    private lateinit var cbSugar: CheckBox
    private lateinit var cbLemon: CheckBox

    private lateinit var spinnerTea: Spinner
    private lateinit var spinnerCoffee: Spinner

    private lateinit var drink: String
    private lateinit var name: String
    private lateinit var password: String

    private lateinit var builderAdditions: StringBuilder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_order)
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name").toString()
            password = intent.getStringExtra("password").toString()
        } else {
            name = getString(R.string.default_name)
            password = getString(R.string.default_password)
        }
        drink = getString(R.string.tea)
        tvWelcoming = findViewById(R.id.tvWelcoming)
        val hello = String.format(getString(R.string.hello_user), name)
        tvWelcoming.text = hello

        tvAdditions = findViewById(R.id.tvAdditions)
        val additions = String.format(getString(R.string.additions), drink)
        tvAdditions.text = additions

        cbMilk = findViewById(R.id.cbMilk)
        cbSugar = findViewById(R.id.cbSugar)
        cbLemon = findViewById(R.id.cbLemon)

        spinnerTea = findViewById(R.id.spinnerTea)
        spinnerCoffee = findViewById(R.id.spinnerCoffee)

        builderAdditions = java.lang.StringBuilder()

    }

    fun onClickChangeDrink(view: View) {
        val button = view as RadioButton
        val id = button.id
        drink = if (id == R.id.rbTea) {
            getString(R.string.tea).also {
                spinnerTea.visibility = View.VISIBLE
                spinnerCoffee.visibility = View.INVISIBLE
                cbLemon.visibility = View.VISIBLE
            }
        } else {
            getString(R.string.coffee).also {
                spinnerCoffee.visibility = View.VISIBLE
                spinnerTea.visibility = View.INVISIBLE
                cbLemon.visibility = View.INVISIBLE
            }
        }
        val additions = String.format(getString(R.string.additions), drink)
        tvAdditions.text = additions
    }

    fun onClickSendOrder(view: View) {
        builderAdditions.setLength(0)

        if (cbMilk.isChecked) {
            builderAdditions.append(getString(R.string.milk)).append(" ")
        }
        if (cbSugar.isChecked) {
            builderAdditions.append(getString(R.string.sugar)).append(" ")
        }
        if (cbLemon.isChecked && drink == getString(R.string.tea)) {
            builderAdditions.append(getString(R.string.lemon)).append(" ")
        }
        var optionOfDrink = ""
        if (drink == getString(R.string.tea)) {
            optionOfDrink = spinnerTea.selectedItem.toString()
        } else {
            optionOfDrink = spinnerCoffee.selectedItem.toString()
        }
        val order = String.format(
            getString(R.string.order),
            name,
            password,
            drink,
            optionOfDrink
        )

        var additions: String = if (builderAdditions.isNotEmpty()) {
            getString(R.string.need_additions) + builderAdditions
        } else {
            ""
        }
        val fullOrder = order + additions
        val intent = Intent(this, OrderDetailActivity::class.java)
        intent.putExtra("order", fullOrder)
        startActivity(intent)
    }
}