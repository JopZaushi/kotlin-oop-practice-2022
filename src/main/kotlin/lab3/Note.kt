package lab3

sealed class Note(open val title: String, open val date: Date) {

    class TextNote(
        override val title: String,
        private val content: String,
        override val date: Date,
    ) : Note(title, date) {
        override fun toString(): String {
            return "TextNote: $title\n" +
                    "$content\n" +
                    "$date\n"
        }

    }

    class Task(
        override val title: String,
        private val content: String,
        private val deadline: String,
        override val date: Date,
    ) : Note(title, date) {
        override fun toString(): String {
            return "Task: $title||Deadline: $deadline\n" +
                    "$content\n" +
                    "$date\n"
        }
    }

    class Link(
        override val title: String,
        private val content: String,
        private val url: String,
        override val date: Date,
    ) : Note(title, date) {
        override fun toString(): String {
            return "Link: $title  $url\n" +
                    "$content\n" +
                    "$date\n"
        }
    }


}