package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.model

import java.sql.Time
import java.time.LocalDate

class Task {

    var id = 0
    var taskName: String? = null
    var startTime: Long? = null
    var endTime: Long? = null
    var duration: Long? = null
    var date: LocalDate? = null
    var project: List<Project>? = null
    var label: List<Label>? = null

}