package runtime

import runtime.types.*

class Environment (parentENV: Environment?) {
    private val parent: Environment? = parentENV
    private val variables:MutableMap<String, RuntimeVal>  = mutableMapOf()

    fun declareVar(varName: String, value: RuntimeVal): RuntimeVal {
        if(this.variables.containsKey(varName)) {
            throw Error("Cannot declare variable $varName as it already is defined.")
        }

        this.variables[varName] = value
        return value
    }

    fun assignVar(varName: String, value: RuntimeVal): RuntimeVal {
        val env = this.resolve(varName)
        env.variables[varName] = value

        return value
    }

    fun lookupVar(varName: String): RuntimeVal {
        val env = this.resolve(varName)

        return env.variables[varName] as RuntimeVal
    }

    private fun resolve(varName: String): Environment {
        if(this.variables.containsKey(varName))
            return this
        if(this.parent == null)
            throw Error("Cannot resolve '$varName' as it does not exist")
        return this.parent.resolve(varName)
    }
}