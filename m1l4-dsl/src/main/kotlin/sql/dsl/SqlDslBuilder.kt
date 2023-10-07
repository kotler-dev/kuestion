package sql.dsl

@DslMarker
annotation class SqlSelectQuery

@DslMarker
annotation class SqlBuilderDsl

@DslMarker
annotation class SqlSelectMethod

@DslMarker
annotation class SqlContext

@SqlBuilderDsl
open class SqlSelectBuilder {
    private var select: String = "*"
    private var from: String? = null
    private var where: String = ""
    private var orderBy: String? = null

    @SqlSelectMethod
    fun select(vararg field: String) {
        select = field.joinToString(separator = ", ", prefix = "", postfix = "")
    }

    @SqlSelectMethod
    fun from(field: String) {
        from = field
    }

    @SqlSelectMethod
    fun where(block: SqlWhereContext.() -> Unit) {
        val context = SqlWhereContext().apply(block)
        where = context.build()
    }

    fun build(): String {
        from?.let {
            val select = "select ${this.select}"
            val from = "from ${this.from}"
            val where = if (this.where.isNotEmpty()) "where ${this.where}" else ""
            val orderBy = "order by ${this.orderBy}"
            println("$select $from $where".trim())
            return "$select $from $where $orderBy".trim()
        } ?: throw Exception()
    }

    fun orderBy(field: String) {
        orderBy = field
    }
}

class SqlWhereContext : SqlWhereBuilder() {
    @SqlContext
    fun or(block: OrContext.() -> Unit) {
        addExpression("(${OrContext().apply(block).build(WhereSeparator.OR)})")
    }

    @SqlContext
    fun and(block: AndContext.() -> Unit) {
        addExpression("(${AndContext().apply(block).build(WhereSeparator.AND)})")
    }
}

class OrContext : SqlWhereBuilder()
class AndContext : SqlWhereBuilder()

@SqlBuilderDsl
open class SqlWhereBuilder {

    private val expressions = mutableListOf<String>()

    fun addExpression(expression: String) {
        expressions += expression
    }

    infix fun String.eq(field: Any?) {
        expressions += when (field) {
            is Number -> "$this = $field"
            is String -> "$this = '$field'"
            null -> "$this is not null"
            else -> throw Exception("Wrong field: $field")
        }
    }

    infix fun String.nonEq(field: Any?) {
        expressions += when (field) {
            is Number -> "$this <> $field"
            is String -> "$this <> '$field'"
            null -> "$this is not null"
            else -> throw Exception("Wrong field: $field")
        }
    }

    enum class WhereSeparator(val separator: String) {
        AND("and"),
        OR("or")
    }

    fun build(whereSeparator: WhereSeparator = WhereSeparator.AND): String {
        return expressions.joinToString(" ${whereSeparator.separator} ")
    }
}

@SqlSelectQuery
fun query(block: SqlSelectBuilder.() -> Unit): String {
    return SqlSelectBuilder().apply(block).build()
}

@Suppress("unused")
class SqlDslBuilder {}