package runtime.types

interface NumberValType : RuntimeValType {
    override val type: ValueType
        get() = ValueType.Number
    val value: Double
}