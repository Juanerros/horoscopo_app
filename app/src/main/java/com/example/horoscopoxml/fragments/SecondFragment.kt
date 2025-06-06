package com.example.horoscopoxml.fragments

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.horoscopoxml.R
import com.example.horoscopoxml.data.horoscopoData
import com.example.horoscopoxml.data.signosData
import com.google.android.material.button.MaterialButton
import kotlin.random.Random
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.VibrationEffect
import android.os.VibratorManager

class SecondFragment : Fragment(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastUpdate: Long = 0
    private var lastX: Float = 0.0f
    private var lastY: Float = 0.0f
    private var lastZ: Float = 0.0f
    private val shakeThreshold = 1500
    private var ready = false
    private lateinit var root: View
    private var selectedSigno:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_second, container, false)

        sensorManager = requireActivity().getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val btnNav = root.findViewById<MaterialButton>(R.id.btnNav2)
        btnNav.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        val btnCalcular = root.findViewById<MaterialButton>(R.id.btnCalcular)
        btnCalcular.setOnClickListener {
            setHoroscopoText()
        }

        return root
    }

    override fun onStart() {
        super.onStart()

        handleList()
    }

    private fun handleList() {
        val signos = signosData.listSignos
        val dropdown = root.findViewById<AutoCompleteTextView>(R.id.listSignos)
        val nameSignos = signos.map { it.name }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            nameSignos
        )

        dropdown.setAdapter(adapter)

        dropdown.setOnItemClickListener { parent, _, position, _ ->
            selectedSigno = parent.getItemAtPosition(position).toString()
            ready = true
        }
    }

    private fun vibratePhoneModern() {
        val vibratorManager = requireContext().getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        val vibrator = vibratorManager.defaultVibrator

        if (vibrator.hasVibrator()) {
            val vibrationPattern = longArrayOf(0, 500, 200, 500)

            val vibrationEffect = VibrationEffect.createWaveform(
                vibrationPattern,
                -1
            )

            vibrator.vibrate(vibrationEffect)
        }

        setHoroscopoText()
    }

    private fun setHoroscopoText() {
        if(!ready) return

        val txtH = root.findViewById<TextView>(R.id.txtHoroscopo)
        val rnd = Random.nextInt(0, 13)
        val newText = horoscopoData.listHoroscopo[rnd]
        txtH.text = newText

        ready = false
    }

    override fun onResume() {
        super.onResume()
        // Registra el listener del sensor
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        // Detiene la detección para ahorrar batería
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                val currentTime = System.currentTimeMillis()
                if ((currentTime - lastUpdate) > 500) { // Evita saturación
                    val diffTime = currentTime - lastUpdate
                    lastUpdate = currentTime

                    val x = it.values[0]
                    val y = it.values[1]
                    val z = it.values[2]

                    val speed = Math.abs(x + y + z - lastX - lastY - lastZ) / diffTime * 10000

                    if (speed > shakeThreshold) {
                        vibratePhoneModern()
                    }

                    lastX = x
                    lastY = y
                    lastZ = z
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // locura la programacion orientada a objetos :v
    }

}