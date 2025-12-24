package ru.example.prac9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prac9.databinding.Fragment2Binding
import kotlin.math.sqrt

class Fragment2 : Fragment() {
    private var _binding: Fragment2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateButton.setOnClickListener {
            calculateDistance()
        }
    }

    private fun calculateDistance() {
        try {
            val x1 = binding.inputX1.text.toString().toDouble()
            val y1 = binding.inputY1.text.toString().toDouble()
            val x2 = binding.inputX2.text.toString().toDouble()
            val y2 = binding.inputY2.text.toString().toDouble()

            val dx = x2 - x1
            val dy = y2 - y1
            val distance = sqrt(dx * dx + dy * dy)

            binding.resultDistance.text = String.format("Расстояние: %.2f", distance)
            binding.formulaText.text = """
                Точка 1: ($x1, $y1)
                Точка 2: ($x2, $y2)
                
                Расстояние d = √[(x₂ - x₁)² + (y₂ - y₁)²]
                d = √[($x2 - $x1)² + ($y2 - $y1)²]
                d = √[(${String.format("%.2f", dx)})² + (${String.format("%.2f", dy)})²]
                d = √[${String.format("%.2f", dx * dx)} + ${String.format("%.2f", dy * dy)}]
                d = √[${String.format("%.2f", dx * dx + dy * dy)}]
                d = ${String.format("%.2f", distance)}
            """.trimIndent()

        } catch (e: NumberFormatException) {
            binding.resultDistance.text = "Ошибка: введите числа!"
            binding.formulaText.text = "Пожалуйста, введите координаты всех точек"
        } catch (e: Exception) {
            binding.resultDistance.text = "Ошибка вычисления!"
            binding.formulaText.text = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}