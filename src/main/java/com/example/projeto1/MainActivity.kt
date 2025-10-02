package com.example.projeto1

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Elementos da interface
        val inpCapitalInicial = findViewById<EditText>(R.id.edit_capital_inicial)
        val inpTaxaDeJuros = findViewById<EditText>(R.id.edit_taxa_de_juros)
        val inpTempo = findViewById<EditText>(R.id.edit_tempo)
        val checkboxComposto = findViewById<CheckBox>(R.id.checkbox_composto)
        val btLimpar = findViewById<Button>(R.id.button_limpar)
        val btCalcular = findViewById<Button>(R.id.button_calcular)
        val resultadoDisplay = findViewById<TextView>(R.id.text_resultado_display)

        // Evento do botão de limpar
        btLimpar.setOnClickListener {
            inpCapitalInicial.setText("")
            inpTaxaDeJuros.setText("")
            inpTempo.setText("")
        }

        // Evento do botão de calcular
        btCalcular.setOnClickListener {
                try {
                    val c = inpCapitalInicial.text.toString().toDouble()
                    val i = inpTaxaDeJuros.text.toString().toDouble()/100
                    val t = inpTempo.text.toString().toDouble()
                    var montante: Double = 0.0
                    var juros: Double = 0.0
                    // Juros composto
                    if(checkboxComposto.isChecked){
                        montante = c * (1 + i).pow(t)
                        juros = montante - c
                    }
                    // Juros simples
                    else{
                        montante = c * (1 + (i*t))
                        juros = montante - c
                    }
                    val fmtResultadoDisplay = "Montante: %.2f | Juros: %.2f".format(montante, juros)
                    resultadoDisplay.text = fmtResultadoDisplay
                } catch (exc: Exception) {
                    resultadoDisplay.text = "Erro"
                }
        }
    }
}