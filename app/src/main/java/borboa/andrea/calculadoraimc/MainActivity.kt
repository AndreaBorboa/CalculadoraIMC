package borboa.andrea.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables
        var lblResultado: TextView = findViewById(R.id.textView_imc)
        var lblEstado: TextView = findViewById(R.id.tv_estado)

        //EditText
        var txtEstatura: EditText = findViewById(R.id.editTextNumberDecimal_estatura)
        var txtPeso: EditText = findViewById(R.id.editTextNumberDecimal_peso)
        var btnCalcular: Button = findViewById(R.id.button_imc)

        btnCalcular.setOnClickListener {
            if(!txtEstatura.text.isBlank() || !txtPeso.text.isBlank()){
                //Calcula IMC y Muestra Resultado
                val imcNum = calcularIMC(txtEstatura.text.toString().toDouble(),
                txtPeso.text.toString().toDouble())
                lblResultado.setText(imcNum.toString())

                val estado = this.obtenEstado(imcNum)
                lblEstado.setText(estado)

                when(estado) {
                    "Bajo peso" -> lblEstado.setBackgroundResource(R.color.colorBrown)
                    "Saludable" -> lblEstado.setBackgroundResource(R.color.colorGreen)
                    "Sobrepeso" -> lblEstado.setBackgroundResource(R.color.colorGreenish)
                    "Obesidad de grado 1" -> lblEstado.setBackgroundResource(R.color.colorYellow)
                    "Obesidad de grado 2" -> lblEstado.setBackgroundResource(R.color.colorOrange)
                    "Obesidad de grado 3" -> lblEstado.setBackgroundResource(R.color.colorRed)

                }
            }
        }
    }

    fun calcularIMC(altura:Double, peso:Double):Double{
        val imc: Double= (peso/Math.pow(altura, 2.0))
        return imc
    }

    fun obtenEstado(imc:Double):String{
        when{
            imc <18.5 -> return "bajo peso"
            imc >=18.5 && imc <= 24.9 -> return "Saludable"
            imc >=24.9 && imc <=29.9 -> return "Sobrepeso"
            imc >= 29.9 && imc <= 3.9 -> return "Obesidad grado 1"
            imc >= 34.9 && imc <= 40 -> return "Obesidad grado 2"
            imc >= 40 -> return "Obesidad grado 3"
        }
        return "Error"
    }
}