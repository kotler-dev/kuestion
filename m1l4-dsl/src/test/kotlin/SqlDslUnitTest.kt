import org.junit.Test
import sql.dsl.query
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SqlDslUnitTest {
    private fun checkSQL(expected: String, sql: String) {
        assertEquals(expected, sql)
    }

    @Test
    fun `simple select all from table`() {
        val expected = "select * from table"

        val actual = query {
            select("*")
            from("table")
        }

        checkSQL(expected, actual)
    }

    @Test
    fun `simple select2 all from table`() {
        val expected = "select col_a from table"

        val actual = query {
            select("col_a")
            from("table")
        }

        checkSQL(expected, actual)
    }

    @Test
    fun `check that select can't be used without table`() {
        assertFailsWith<Exception> {
            query {
                select("col_a")
            }
        }
    }

    @Test
    fun `select certain columns from table`() {
        val expected = "select col_a, col_b from table"

        val actual = query {
            select("col_a", "col_b")
            from("table")
        }

        checkSQL(expected, actual)
    }

    @Test
    fun `select certain columns from table 1`() {
        val expected = "select col_a, col_b from table"

        val actual = query {
            select("col_a", "col_b")
            from("table")
        }

        checkSQL(expected, actual)
    }

    /**
     * __eq__ is "equals" function. Must be one of char:
     *  - for strings - "="
     *  - for numbers - "="
     *  - for null - "is"
     */
    @Test
    fun `select with complex where condition with one condition`() {
        val expected = "select * from table where col_a = 'id'"

        val actual = query {
            select("col_a")
            from("table")
            where {
                "col_a" eq "id"
            }
        }

        checkSQL(expected, actual)
    }

    /**
     * __nonEq__ is "non equals" function. Must be one of chars:
     *  - for strings - "!="
     *  - for numbers - "!="
     *  - for null - "!is"
     */
    @Test
    fun `select with complex where condition with two conditions`() {
        val expected = "select * from table where col_a <> 0"

        val actual = query {
            from("table")
            where {
                "col_a" nonEq 0
            }
        }

        checkSQL(expected, actual)
    }

    @Test
    fun `when 'or' conditions are specified then they are respected`() {
        val expected = "select * from table where (col_a = 4 or col_b is not null)"

        val actual = query {
            from("table")
            where {
                or {
                    "col_a" eq 4
                    "col_b" nonEq null
                }
            }
        }
        checkSQL(expected, actual)
    }

    @Test
    fun `all operators`() {
        val expected =
            "select name, age " +
                    "from users " +
                    "where name = 'Anna' " +
                    "and (age = 15 or age <> 20) " +
                    "and (height = 170 and eye_color <> 'green') " +
                    "order by address"

        val actual = query {
            select("name", "age")
            from("users")
            where {
                "name" eq "Anna"
                or {
                    "age" eq 15
                    "age" nonEq 20
                }
                and {
                    "height" eq 170
                    "eye_color" nonEq "green"
                }
            }
            orderBy("address")
        }

        checkSQL(expected, actual)
    }
}