import org.junit.Test
import user.dsl.buildUser
import user.models.Action

class UserTestCase {

    @Test
    fun `test user`() {
        val user = buildUser {
            name {
                first = "Anton"
                last = "Kotler"
            }
            contacts {
                email = "kotler@dev"
                phone = "81234567890"
            }
            actions {
                add(Action.UPDATE)
                add(Action.DELETE)

                +Action.DELETE
                +Action.READ
            }
            availability {
                monday("9:00")
                friday("10:00")
            }
        }

        println(user)
    }
}