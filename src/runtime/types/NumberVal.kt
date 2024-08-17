package runtime.types

data class NumberVal(override val type: ValueType, override val value: Double) : NumberValType
