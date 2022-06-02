package course_work.lab3

import java.time.LocalDate

sealed class CWNote(open val title: String, open val date: LocalDate) {

    class TextNote(
        override val title: String,
        val content: String,
        override val date: LocalDate,
    ) : CWNote(title, date) {
        override fun toString(): String {
            return "TextNote: $title\n" +
                    "$content\n" +
                    "$date\n"
        }

    }

    class Task(
        override val title: String,
        val content: String,
        val deadline: String,
        override val date: LocalDate,
    ) : CWNote(title, date) {
        override fun toString(): String {
            return "Task: $title||Deadline: $deadline\n" +
                    "$content\n" +
                    "$date\n"
        }
    }

    class Link(
        override val title: String,
        val content: String,
        val url: String,
        override val date: LocalDate,
    ) : CWNote(title, date) {
        override fun toString(): String {
            return "Link: $title  $url\n" +
                    "$content\n" +
                    "$date\n"
        }
    }


}