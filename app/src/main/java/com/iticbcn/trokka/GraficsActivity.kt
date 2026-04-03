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
            FirebaseConnector.cargarInfo("deice1")
        }

        initGrafico(FirebaseConnector.dataInfo)


        binding.imgFlechita.setOnClickListener { finish() }

    }

    private fun initGrafico(usageInfo: UsageInfo) {
        val entries = ArrayList<BarEntry>()

        entries.add(BarEntry(0f, usageInfo.countFavoritos.toFloat()))
        entries.add(BarEntry(1f, usageInfo.countEntradesApp.toFloat()))
        entries.add(BarEntry(2f, usageInfo.countCrearObjeto.toFloat()))
        entries.add(BarEntry(3f, usageInfo.countVisitasMapa.toFloat()))
        entries.add(BarEntry(4f, usageInfo.horasUso.toFloat()))

        val barDataSet = BarDataSet(entries, "Ús de l'app")

        val pastelColors = listOf(
            Color.BLUE,
            Color.GREEN,
            Color.YELLOW,
            Color.RED,
            Color.MAGENTA
        )
        barDataSet.colors=pastelColors
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f
        barDataSet.valueFormatter= DefaultValueFormatter(0)

        val nombresEjeX = listOf("Cant. de favoritos", "Visitas app", "Obj. Creados", "Visitas a mapa", "Hores d'ús")

        binding.bcGrafic.apply {
            data= BarData(barDataSet)
            //setDrawGridBackground(false)

            xAxis.valueFormatter = IndexAxisValueFormatter(nombresEjeX)
            xAxis.granularity = 1f
            xAxis.isGranularityEnabled = true
            xAxis.labelRotationAngle = 15f
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            axisLeft.setDrawGridLines(false)
            axisRight.setDrawAxisLine(false)
            axisRight.setDrawGridLines(false)
            axisRight.setDrawLabels(false)
            description.text="Estadísticas d'ús de l'app"
            description.isEnabled = false // Activa la descripció
            setFitBars(true) // Ajusta les barres al gràfic
            animateY(1000) // Animació en Y
            invalidate()
        }
    }
}