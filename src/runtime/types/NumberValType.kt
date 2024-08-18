package runtime.types

interface NumberValType : RuntimeVal {
    override val type: ValueType
        get() = ValueType.Number
    val value: Double
}