package ru.example.prac9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prac9.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateButton.setOnClickListener {
            calculateCircle()
        }
    }

    private fun calculateCircle() {
        try {
            val radius = binding.inputRadius.text.toString().toDouble()

            val pi = 3.14

            val circumference = 2 * pi * radius

            val area = pi * radius * radius

            binding.resultCircumference.text = String.format("Длина окружности: %.2f", circumference)
            binding.resultArea.text = String.format("Площадь круга: %.2f", area)
            binding.formulaText.text = """
                Используем π = 3.14
                
                Длина окружности (L):
                L = 2 × π × R = 2 × 3.14 × $radius = ${String.format("%.2f", circumference)}
                
                Площадь круга (S):
                S = π × R² = 3.14 × ${String.format("%.2f", radius)}² = ${String.format("%.2f", area)}
            """.trimIndent()

        } catch (e: NumberFormatException) {
            binding.resultCircumference.text = "Ошибка: введите число!"
            binding.resultArea.text = ""
            binding.formulaText.text = "Пожалуйста, введите корректное значение радиуса"
        } catch (e: Exception) {
            binding.resultCircumference.text = "Ошибка вычисления!"
            binding.resultArea.text = ""
            binding.formulaText.text = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}