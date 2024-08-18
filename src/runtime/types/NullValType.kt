package runtime.types

interface NullValType : RuntimeVal {
    override val type: ValueType
        get() = ValueType.Null
    val value: String?
}