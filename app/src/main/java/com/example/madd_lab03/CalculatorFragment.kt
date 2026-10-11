package com.example.madd_lab03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.DecimalFormat
import java.util.Locale

class CalculatorFragment : Fragment() {

    private lateinit var etNumber1: EditText
    private lateinit var etNumber2: EditText
    private lateinit var btnPlus: Button
    private lateinit var btnMinus: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etNumber1 = view.findViewById(R.id.etNumber1)
        etNumber2 = view.findViewById(R.id.etNumber2)
        btnPlus = view.findViewById(R.id.btnPlus)
        btnMinus = view.findViewById(R.id.btnMinus)
        btnMultiply = view.findViewById(R.id.btnMultiply)
        btnDivide = view.findViewById(R.id.btnDivide)

        btnPlus.setOnClickListener {
            computeAndDisplay { a, b -> a + b }
        }

        btnMinus.setOnClickListener {
            computeAndDisplay { a, b -> a - b }
        }

        btnMultiply.setOnClickListener {
            computeAndDisplay { a, b -> a * b }
        }

        btnDivide.setOnClickListener {
            val num2Text = etNumber2.text.toString().trim()
            val num2 = num2Text.toDoubleOrNull()
            if (num2 != null && num2 == 0.0) {
                etNumber2.error = getString(R.string.msg_divide_by_zero)
                Toast.makeText(requireContext(), getString(R.string.msg_divide_by_zero), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            computeAndDisplay { a, b -> a / b }
        }
    }

    private fun computeAndDisplay(operation: (Double, Double) -> Double) {
        val text1 = etNumber1.text.toString().trim()
        val text2 = etNumber2.text.toString().trim()

        if (text1.isEmpty()) {
            etNumber1.error = getString(R.string.hint_number_1)
            etNumber1.requestFocus()
            Toast.makeText(requireContext(), getString(R.string.msg_empty_fields), Toast.LENGTH_SHORT).show()
            return
        }

        if (text2.isEmpty()) {
            etNumber2.error = getString(R.string.hint_number_2)
            etNumber2.requestFocus()
            Toast.makeText(requireContext(), getString(R.string.msg_empty_fields), Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = text1.toDoubleOrNull()
        val num2 = text2.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(requireContext(), getString(R.string.msg_invalid_number), Toast.LENGTH_SHORT).show()
            return
        }

        val result = operation(num1, num2)
        val formattedResult = formatResult(result)

        val resultFragment = ResultFragment.newInstance(formattedResult)
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.fragment_container, resultFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun formatResult(value: Double): String {
        return if (value % 1.0 == 0.0) {
            String.format(Locale.US, "%.1f", value)
        } else {
            val df = DecimalFormat("#.####")
            df.format(value)
        }
    }
}
