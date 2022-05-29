package course_work

import course_work.view.CourseWorkUi
import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        val ticTacToeUi = CourseWorkUi()
        ticTacToeUi.setLocationRelativeTo(null)
        ticTacToeUi.isVisible = true
    }
}