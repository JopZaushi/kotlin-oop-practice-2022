package course_work.view

import course_work.model.Cell
import course_work.model.GAME_NOT_FINISHED
import course_work.model.Model
import course_work.model.ModelChangeListener
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.GridLayout
import javax.swing.*
import javax.swing.table.DefaultTableModel


private const val GAP = 15

class CourseWorkUi : JFrame("Notes"), ModelChangeListener {
    private var gameModel: Model = Model()
    private val statusLabel = JLabel("Status", JLabel.CENTER)
    private val buttons = mutableListOf<MutableList<JButton>>()

    private val model = DefaultTableModel()
    private var table = JTable(model)

    //конструктор главной панели
    init {
        setSize(400, 700)
        defaultCloseOperation = EXIT_ON_CLOSE
        val name: Array<String> = arrayOf("Вид заметки", "Название", "Дата создания")
        model.setColumnIdentifiers(name)
        model.addRow(name)
        updateFont(statusLabel, 20.0f)
        rootPane.contentPane = JPanel(BorderLayout(GAP, GAP)).apply {
            add(statusLabel, BorderLayout.NORTH)
            add(table, BorderLayout.CENTER)
            add(createCreateButton(), BorderLayout.SOUTH)
            //border = BorderFactory.createEmptyBorder(GAP, 80, GAP, 80)
        }

        resubscribe()
    }


    private fun createCreateButton(): Component {
        val createButton = JButton("+")
        updateFont(createButton, 20.0f)
        createButton.addActionListener {
            if (gameModel.state in GAME_NOT_FINISHED) {
                /*val creationNote = JOptionPane.showInputDialog(
                    this,
                    "Выберите заметку",
                    "Создание",
                    JOptionPane.QUESTION_MESSAGE,
                    icon,
                    note.toTypedArray(),
                    note[0]
                )*/
                createSwitchWindow()
            } else {
                resubscribe()
            }
        }
        return createButton
    }

    private fun createSwitchWindow() {
        val switchWindow = JDialog(this, "Create Note")
        switchWindow.setSize(250, 250)
        switchWindow.layout = GridLayout(4, 1, 20, 0)
        switchWindow.isResizable = false
        switchWindow.setLocationRelativeTo(null)

        //Текстовая заметка
        val buttonTextNote = JButton("Text Note")
        buttonTextNote.addActionListener {
            switchWindow.isVisible = false
            //новое окно для ввода заметки
            val noteWindow = JDialog(this, "Text Note")
            //текстовое поле
            val windowTextNote = JTextArea(200, 250)
            val titleTextNote = JTextField(250)
            //кнопка сохранения
            val saveButton = JButton("Save")
            saveButton.addActionListener {
                noteWindow.isVisible = false
                val data: Array<Array<String>> = Array(1) { Array(3) { "" } }
                data[0] = arrayOf("Text Note", titleTextNote.text, "01.02.2002")
                model.addRow(data[0])
            }
            //табуляция
            windowTextNote.lineWrap = true
            //скроллинг
            val scrollPane = JScrollPane(windowTextNote)
            noteWindow.add(scrollPane, BorderLayout.CENTER)
            noteWindow.add(titleTextNote, BorderLayout.NORTH)
            noteWindow.add(saveButton, BorderLayout.SOUTH)
            noteWindow.setSize(250, 350)
            noteWindow.isVisible = true
            noteWindow.setLocationRelativeTo(null)

        }
        switchWindow.add(buttonTextNote)


        val buttonToDoList = JButton("To do list")
        buttonToDoList.addActionListener {

        }
        switchWindow.add(buttonToDoList)


        //Текстовая заметка
        val buttonLink = JButton("Link")
        buttonLink.addActionListener {
            switchWindow.isVisible = false
            //новое окно для ввода заметки
            val noteWindow = JDialog(this, "Link")
            //текстовое поле
            val windowTextNote = JTextArea(200, 250)
            val titleTextNote = JTextField(250)
            //кнопка сохранения
            val saveButton = JButton("Save")
            saveButton.addActionListener {
                noteWindow.isVisible = false
                val data: Array<Array<String>> = Array(1) { Array(3) { "" } }
                data[0] = arrayOf("Link", titleTextNote.text, "01.02.2002")
                model.addRow(data[0])
            }
            //табуляция
            windowTextNote.lineWrap = true
            //скроллинг
            val scrollPane = JScrollPane(windowTextNote)
            noteWindow.add(scrollPane, BorderLayout.CENTER)
            noteWindow.add(titleTextNote, BorderLayout.NORTH)
            noteWindow.add(saveButton, BorderLayout.SOUTH)
            noteWindow.setSize(250, 350)
            noteWindow.isVisible = true
            noteWindow.setLocationRelativeTo(null)

        }
        switchWindow.add(buttonLink)


        val buttonImage = JButton("Image")
        buttonLink.addActionListener {

        }
        switchWindow.add(buttonImage)
        switchWindow.isFocusable = true
        switchWindow.isVisible = true
    }

    //обновление окна
    private fun resubscribe() {
        gameModel.removeModelChangeListener(this)
        gameModel = Model()
        gameModel.addModelChangeListener(this)
        updateGameUI()

    }


    /*private fun createBoardPanel(): Component {
        val gamePanel = JPanel(GridLayout(BOARD_SIZE, BOARD_SIZE, GAP, GAP))

        for (i in 0 until 1) {
            val buttonsRow = mutableListOf<JButton>()
            for (j in 0 until BOARD_SIZE) {
                val cellButton = JButton()
                cellButton.addActionListener {
                    gameModel.doMove(j, i)
                }
                buttonsRow.add(cellButton)
                gamePanel.add(cellButton)
                updateFont(cellButton, 30.0f)
            }
            buttons.add(buttonsRow)
        }

        return gamePanel
    }*/

    private fun updateFont(component: JComponent, newFontSize: Float) {
        val font = component.font
        val derivedFont = font.deriveFont(newFontSize)
        component.font = derivedFont
    }

    override fun onModelChanged() {
        updateGameUI()
    }

    private fun updateGameUI() {
        val state = gameModel.state
        statusLabel.text = state.textValue

        for ((i, buttonRow) in buttons.withIndex()) {
            for ((j, button) in buttonRow.withIndex()) {
                val cell = gameModel.board[i][j]
                button.text = cell.toString()

                button.isEnabled = cell == Cell.EMPTY && state in GAME_NOT_FINISHED
                button.foreground = when (cell) {
                    Cell.X -> Color.BLUE
                    Cell.O -> Color.RED
                    Cell.EMPTY -> Color.BLACK
                }
            }
        }
    }
}