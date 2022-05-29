package course_work

import course_work.view.CourseWorkUi
import java.time.LocalDateTime
import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        val ticTacToeUi = CourseWorkUi()
        ticTacToeUi.setLocationRelativeTo(null)
        ticTacToeUi.isVisible = true
    }
    val data = LocalDateTime.now()
    println(data)
}