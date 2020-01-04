package ru.skillbranch.devintensive

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.extensions.isKeyboardOpen
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var benderImage : ImageView
    lateinit var textTxt : TextView
    lateinit var messageEt : EditText
    lateinit var sendBtn : ImageView

    lateinit var benderObj : Bender

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        Log.d("M_MainActivity","onCreate $status $question")
        val (r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)

//        actionDone()

    }

    private fun actionDone(){
        et_message.setOnEditorActionListener{v, actionId, event ->
            if(et_message.imeOptions == EditorInfo.IME_ACTION_DONE){
                Log.d("M_actionDone","$v")

                updateView()
                true
            } else {
                false
            }

        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity","onRestart ${benderObj.status.name} ${benderObj.question.name}")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity","onStart ${benderObj.status.name} ${benderObj.question.name}")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity","onResume ${benderObj.status.name} ${benderObj.question.name}")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity","onPause  ${benderObj.status.name} ${benderObj.question.name}")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity","onStop  ${benderObj.status.name} ${benderObj.question.name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity","onDestroy  ${benderObj.status.name} ${benderObj.question.name}")
    }

    override fun onClick(v: View?) {
        Log.d("M_OnClick","")
        if (v?.id == R.id.iv_send) {
            updateView()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("STATUS", benderObj.status.name)
        outState.putString("QUESTION", benderObj.question.name)
        Log.d("M_MainActivity","onSaveInstanceState ${benderObj.status.name} ${benderObj.question.name}")
    }

    @SuppressLint("DefaultLocale")
    private fun updateView(){
            val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r, g, b) = color
            benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
            hideKeyboard()
    }

}


