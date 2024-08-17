package runtime.types

interface NullValType : RuntimeValType {
    override val type: ValueType
        get() = ValueType.Null
    val value: String
}