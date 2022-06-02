package course_work.lab3

//To make it easier to sort
class CWDate(val day: Int, val month: Int, val year: Int) {
    override fun toString(): String {
        return "Date of creation: $day.$month.$year"
    }
}