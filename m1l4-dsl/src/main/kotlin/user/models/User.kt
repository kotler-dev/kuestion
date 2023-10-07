package user.models

import java.time.LocalDateTime

@Suppress("unused")
enum class Action {
    CREATE,
    READ,
    UPDATE,
    DELETE
}

data class User(
    val id: String,
    val fistName: String,
    val secondName: String?,
    val lastName: String,
    val phone: String,
    val email: String,
    val action: Set<Action>,
    val availability: List<LocalDateTime>
)


