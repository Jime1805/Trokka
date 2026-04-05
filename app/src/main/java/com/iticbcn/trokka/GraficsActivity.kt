package com.iticbcn.trokka

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.iticbcn.trokka.databinding.ActivityGraficsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraficsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGraficsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraficsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            FirebaseConnector.cargarInfo("marc")
        }

        initRadarGrafico(FirebaseConnector.dataInfo)
        initGraficoHoras(FirebaseConnector.dataInfo)

        binding.imgFlechita.setOnClickListener { finish() }


    }

    private fun initRadarGrafico(usageInfo: UsageInfo) {
        val entries = ArrayList<RadarEntry>()

        // En el RadarChart no hace falta poner la posición X, solo el valor
        entries.add(RadarEntry(usageInfo.countFavoritos.toFloat()))
        entries.add(RadarEntry(usageInfo.countEntradesApp.toFloat()))
        entries.add(RadarEntry(usageInfo.countCrearObjeto.toFloat()))
        entries.add(RadarEntry(usageInfo.countVisitasMapa.toFloat()))

        val radarDataSet = RadarDataSet(entries, "Perfil d'Usuari")

        // El RadarChart suele quedar mejor con un solo color relleno y semitransparente
        radarDataSet.color = Color.rgb(0, 128, 0)
        radarDataSet.fillColor = Color.rgb(0, 153, 0)
        radarDataSet.setDrawFilled(true)
        radarDataSet.fillAlpha = 180
        radarDataSet.lineWidth = 2f

        val nombresEjeX = listOf("Favoritos", "Entrades", "Crear Obj.", "Mapa")

        binding.rcGrafic.apply {
            data = RadarData(radarDataSet)

            xAxis.valueFormatter = IndexAxisValueFormatter(nombresEjeX)
            xAxis.textSize = 14f

            // Configuramos el eje Y (las "telarañas" internas)
            yAxis.setDrawLabels(false) // Oculta los números internos para que quede más limpio
            yAxis.axisMinimum = 0f // Para que el centro del radar sea siempre 0

            description.isEnabled = false // Queda mejor sin descripción superpuesta
            animateXY(1400, 1400)
            invalidate()
        }
    }

    private fun initGraficoHoras(usageInfo: UsageInfo) {
        val entries = ArrayList<BarEntry>()

        // Solo tenemos una entrada. Posición X = 0, Valor Y = horasUso
        entries.add(BarEntry(0f, usageInfo.horasUso.toFloat()))

        val barDataSet = BarDataSet(entries, "Temps d'ús")
        barDataSet.color = Color.MAGENTA // Puedes poner el color que quieras
        barDataSet.valueTextSize = 16f
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueFormatter = DefaultValueFormatter(0) // 0 decimales

        binding.hbcGrafic.apply {
            data = BarData(barDataSet)

            // Configuración del Eje X (Que ahora se dibuja en vertical, a la izquierda)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)
            xAxis.valueFormatter = IndexAxisValueFormatter(listOf("Hores totals"))
            xAxis.granularity = 1f
            xAxis.textSize = 14f

            // Configuración del Eje Y Izquierdo (Que ahora se dibuja en horizontal, abajo)
            axisLeft.setDrawGridLines(false)
            axisLeft.setDrawLabels(false) // Ocultamos los números de abajo para que quede más limpio
            axisLeft.setDrawAxisLine(false)
            axisLeft.axisMinimum = 0f // Importante para que la barra nazca del 0

            // Configuración del Eje Y Derecho (Que ahora se dibuja en horizontal, arriba)
            axisRight.isEnabled = false // Lo desactivamos por completo

            // Configuración visual general
            description.isEnabled = false
            setDrawGridBackground(true)
            setDrawValueAboveBar(true) // Coloca el número (ej. "12") justo a la derecha de la barra
            setFitBars(true)
            animateY(1000) // La animación "Y" hace que la barra crezca de izquierda a derecha
            invalidate()
        }
    }
}