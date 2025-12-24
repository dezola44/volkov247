package ru.example.prac9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prac9.databinding.Fragment3Binding

class Fragment3 : Fragment() {
    private var _binding: Fragment3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateButton.setOnClickListener {
            calculateCandyPrice()
        }
    }

    private fun calculateCandyPrice() {
        try {
            val x = binding.inputX.text.toString().toDouble()
            val a = binding.inputA.text.toString().toDouble()
            val y = binding.inputY.text.toString().toDouble()

            val pricePerKg = a / x

            val priceForY = pricePerKg * y

            binding.resultPricePerKg.text = String.format("Цена за 1 кг: %.2f руб.", pricePerKg)
            binding.resultPriceForY.text = String.format("Цена за %.1f кг: %.2f руб.", y, priceForY)

            binding.formulaText.text = """
                Дано:
                $x кг конфет стоит $a рублей
                
                1. Цена за 1 кг:
                Цена = A / X = $a / $x = ${String.format("%.2f", pricePerKg)} руб./кг
                
                2. Цена за $y кг:
                Цена = (A / X) × Y = ${String.format("%.2f", pricePerKg)} × $y = ${String.format("%.2f", priceForY)} руб.
            """.trimIndent()

        } catch (e: NumberFormatException) {
            binding.resultPricePerKg.text = "Ошибка: введите числа!"
            binding.resultPriceForY.text = ""
            binding.formulaText.text = "Пожалуйста, введите все значения"
        } catch (e: ArithmeticException) {
            binding.resultPricePerKg.text = "Ошибка: X не может быть 0!"
            binding.resultPriceForY.text = ""
            binding.formulaText.text = "Количество конфет (X) должно быть больше 0"
        } catch (e: Exception) {
            binding.resultPricePerKg.text = "Ошибка вычисления!"
            binding.resultPriceForY.text = ""
            binding.formulaText.text = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}