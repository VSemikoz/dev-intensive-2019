package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.DAY

class Bender(var status:Status = Status.NORMAL, var question:Question = Question.NAME) {

    fun askQuestion(): String = when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String): Pair<String, Triple<Int, Int, Int>>{
        return if (question.answer.contains(answer)){
            question = question.nexQuestion()
            "Отлично - правильный ответ!\n${question.question}" to status.color
        } else{
            status = status.nextStatus()
            "Это не правильный ответ!\n${question.question}" to status.color
        }
    }

    enum class Status(val color : Triple<Int, Int, Int>){
        NORMAL(Triple(255,255, 255 )),
        WARNING(Triple(255,120, 0 )),
        DANGER(Triple(255,60, 60 )),
        CRITICAL(Triple(255,255, 0 ));

        fun nextStatus():Status{
            return if (this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }

    enum class Question(val question:String, val answer: List<String>){

        NAME("как меня зовут?", listOf("бендер","bender")) {
            override fun nexQuestion(): Question = PROFESSION
        },
        PROFESSION("назови мою професию?", listOf("сгибальщик","bender")){
            override fun nexQuestion(): Question = MATERIAL
        },
        MATERIAL("из чего я сделан?", listOf("метал","iron")){
            override fun nexQuestion(): Question = BDAY
        },
        BDAY("когда меня создали?", listOf("2993")){
            override fun nexQuestion(): Question = SERIAL
        },
        SERIAL("мой серийный номер?", listOf("2716057")){
            override fun nexQuestion(): Question = IDLE
        },
        IDLE("на этом все, вопросов больше нет", listOf()) {
            override fun nexQuestion(): Question = IDLE
        };

        abstract fun nexQuestion():Question
    }
}