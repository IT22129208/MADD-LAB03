package com.example.madd_lab03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {

    companion object {
        private const val ARG_ANSWER = "arg_answer"

        fun newInstance(answer: String): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle().apply {
                putString(ARG_ANSWER, answer)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvAnswer: TextView = view.findViewById(R.id.tvAnswer)
        val btnBack: Button = view.findViewById(R.id.btnBack)

        val answer = arguments?.getString(ARG_ANSWER) ?: "0.0"
        tvAnswer.text = getString(R.string.answer_format, answer)

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}
