package runtime.types

interface BooleanValType : RuntimeVal {
    override val type: ValueType
        get() = ValueType.Boolean
    val value: Boolean
}