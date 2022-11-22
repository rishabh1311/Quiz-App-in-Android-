package com.example.quizproject

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<QuestionBank>{
        val questionsList = ArrayList<QuestionBank>()

        val que1 = QuestionBank(
            1,
            "What country does this flag belong to?",
            R.drawable.flag_argentina,
            "Argentina",
            "Australia",
            "Singapore",
            "Austria",
            1)

        questionsList.add(que1)

        val que2 = QuestionBank(
            2,
            "What country does this flag belong to?",
            R.drawable.flag_australia,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            2)

        questionsList.add(que2)

        val que3 = QuestionBank(
            3,
            "What country does this flag belong to?",
            R.drawable.flag_belgium,
            "Bahamas",
            "Belgium",
            "Barbados",
            "Belize",
            2)

        questionsList.add(que3)

        val que4 = QuestionBank(
            4,
            "What country does this flag belong to?",
            R.drawable.flag_brazil,
            "Belarus",
            "Belize",
            "Brunei",
            "Brazil",
            4)

        questionsList.add(que4)

        val que5 = QuestionBank(
            5,
            "What country does this flag belong to?",
            R.drawable.flag_denmark,
            "Dominica",
            "Egypt",
            "Denmark",
            "Ethiopia",
            3)

        questionsList.add(que5)

        val que6 = QuestionBank(
            6,
            "What country does this flag belong to?",
            R.drawable.flag_fiji,
            "Gabon",
            "Fiji",
            "France",
            "Finland",
            2)

        questionsList.add(que6)

        val que7 = QuestionBank(
            7,
            "What country does this flag belong to?",
            R.drawable.flag_germany,
            "Germany",
            "Georgia",
            "Greece",
            "none of these",
            1)

        questionsList.add(que7)

        val que8 = QuestionBank(
            8,
            "What country does this flag belong to?",
            R.drawable.flag_india,
            "Ireland",
            "Iran",
            "India",
            "Hungary",
            3)

        questionsList.add(que8)

        val que9 = QuestionBank(
            9,
            "What country does this flag belong to?",
            R.drawable.flag_kuwait,
            "Kuwait",
            "Jordon",
            "Sudan",
            "Palestine",
            1)

        questionsList.add(que9)

        val que10 = QuestionBank(
            10,
            "What country does this flag belong to?",
            R.drawable.flag_newzealand,
            "Australia",
            "New Zealand",
            "Tuvalu",
            "United States of America",
            2)

        questionsList.add(que10)

        return questionsList
    }
}