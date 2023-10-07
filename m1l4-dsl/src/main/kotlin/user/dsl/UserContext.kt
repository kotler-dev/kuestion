package user.dsl

import user.models.Action
import user.models.User
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.TemporalAdjusters
import java.util.UUID

class NameContext {
    var first = ""
    var second: String? = null
    var last = ""
}

class ContactsContext {
    var email = ""
    var phone = ""
}

class ActionsContext {
    private val _action = mutableSetOf<Action>()

    val actions: Set<Action>
        get() = _action.toSet()

    fun add(action: Action) = _action.add(action)

    operator fun Action.unaryPlus() = add(this)
}

@Suppress("unused")
class AvailabilityContext {
    private val _availabilities = mutableSetOf<LocalDateTime>()

    val availabilities: List<LocalDateTime>
        get() = _availabilities.toList()

    private fun dayTimeOfWeek(dayOfWeek: DayOfWeek, time: String) {
        val dDay = LocalDate.now().with(TemporalAdjusters.next(dayOfWeek))
        val dTime = time.split(":")
            .map { it.toInt() }
            .let { LocalTime.of(it[0], it[1]) }
        val dDayTime = LocalDateTime.of(dDay, dTime)
        _availabilities.add(dDayTime)
    }

    fun sunday(time: String) = dayTimeOfWeek(DayOfWeek.SUNDAY, time)
    fun monday(time: String) = dayTimeOfWeek(DayOfWeek.MONDAY, time)
    fun tuesday(time: String) = dayTimeOfWeek(DayOfWeek.TUESDAY, time)
    fun wednesday(time: String) = dayTimeOfWeek(DayOfWeek.WEDNESDAY, time)
    fun thursday(time: String) = dayTimeOfWeek(DayOfWeek.THURSDAY, time)
    fun friday(time: String) = dayTimeOfWeek(DayOfWeek.FRIDAY, time)
    fun saturday(time: String) = dayTimeOfWeek(DayOfWeek.SATURDAY, time)
}

class UserContext {

    private val id = UUID.randomUUID().toString()

    private val nameContext = NameContext()
    private val contactsContext = ContactsContext()
    private val actionsContext = ActionsContext()
    private val availabilityContext = AvailabilityContext()

    fun build(): User {
        return User(
            id,
            nameContext.first, nameContext.second, nameContext.last,
            contactsContext.phone, contactsContext.email,
            actionsContext.actions,
            availabilityContext.availabilities
        )
    }

    fun name(block: NameContext.() -> Unit) {
        nameContext.block()
    }

    fun contacts(block: ContactsContext.() -> Unit) {
        contactsContext.block()
    }

    fun actions(block: ActionsContext.() -> Unit) {
        actionsContext.block()
    }

    fun availability(block: AvailabilityContext.() -> Unit) {
        availabilityContext.block()
    }
}

fun buildUser(block: UserContext.() -> Unit) : User {
    return UserContext().apply(block).build()
}



