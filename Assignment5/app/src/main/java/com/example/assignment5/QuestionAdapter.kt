package com.example.assignment5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.assignment5.databinding.LayoutQuestionsCheckBinding
import com.example.assignment5.databinding.LayoutQuestionsRadioBinding

class QuestionAdapter(private val context: Context, private val questions: List<Question>,
    val checkedChangeListener: (question: Question, checkedId: Int, isChecked: Boolean) -> Unit = { _, _,_ -> },
    )
    : ArrayAdapter<Question>(context,0, questions) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var myConvertView: View? = convertView

        val question = questions[position]

        if(myConvertView == null) {

            when (question.questionType) {
                QuestionType.RADIO -> {
                    val radioBinding = LayoutQuestionsRadioBinding.inflate(LayoutInflater.from(context),parent, false)
                    myConvertView = radioBinding.root
                    radioBinding.tvQuestion.text = question.question
                    radioBinding.rgOptions.setOnCheckedChangeListener { _, checkedId ->
                        checkedChangeListener(question, checkedId, true)
                    }
                }
                QuestionType.CHECKBOX -> {
                    val checkBinding = LayoutQuestionsCheckBinding.inflate(LayoutInflater.from(context),parent, false)
                    myConvertView = checkBinding.root
                    checkBinding.tvQuestion.text = question.question
                    addCheckChangeListeners(checkBinding,question)
                }
            }

        }

        return myConvertView
    }

    private fun addCheckChangeListeners(
        checkBinding: LayoutQuestionsCheckBinding,
        question: Question
    ) {
        checkBinding.rbOption1.setOnCheckedChangeListener { _, isChecked ->
            checkedChangeListener(question, 0, isChecked)
        }
        checkBinding.rbOption2.setOnCheckedChangeListener { _, isChecked ->
            checkedChangeListener(question, 1, isChecked)
        }
        checkBinding.rbOption3.setOnCheckedChangeListener { _, isChecked ->
            checkedChangeListener(question, 2, isChecked)
        }
        checkBinding.rbOption4.setOnCheckedChangeListener { _, isChecked ->
            checkedChangeListener(question, 3, isChecked)
        }
    }

}