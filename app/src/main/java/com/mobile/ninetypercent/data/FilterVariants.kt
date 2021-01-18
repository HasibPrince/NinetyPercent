package com.mobile.ninetypercent.data

//val SHAPES = listOf("DRESSES")

enum class Shapes : Value {
    DRESSES {
        override fun value(): String {
            return "Dresses"
        }
    },
    SLEEPWEAR {
        override fun value(): String {
            return "Sleepwear"
        }
    };
}

enum class Style : Value {
    CAMIS {
        override fun value(): String {
            return "Camis"
        }
    },
    SHIRTS {
        override fun value(): String {
            return "Shirts"
        }
    },
    LONG_SLEEVE {
        override fun value(): String {
            return "Long Sleeve"
        }
    },
    MAXI {
        override fun value(): String {
            return "Maxi"
        }
    },
    MIDI {
        override fun value(): String {
            return "Midi"
        }
    },
    MINI {
        override fun value(): String {
            return "Mini"
        }
    },
    KNITTED {
        override fun value(): String {
            return "Knitted"
        }
    };
}

enum class Colors : Value {
    RED {
        override fun value(): String {
            return "#F44336"
        }
    },
    BLACK {
        override fun value(): String {
            return "#000000"
        }
    },
    YELLOW {
        override fun value(): String {
            return "#FFEB3B"
        }
    },
    BLUE {
        override fun value(): String {
            return "#FF3700B3"
        }
    },
    PINK {
        override fun value(): String {
            return "#9C27B0"
        }
    },
    GREY {
        override fun value(): String {
            return "#686868"
        }
    }
}

enum class Sizes : Value {
    XS {
        override fun value(): String {
            return "XS"
        }
    },
    XXS {
        override fun value(): String {
            return "XXS"
        }
    },
    S {
        override fun value(): String {
            return "S"
        }
    },
    M {
        override fun value(): String {
            return "M"
        }
    },
    L {
        override fun value(): String {
            return "L"
        }
    },
    XL {
        override fun value(): String {
            return "XL"
        }
    }
}

enum class Material: Value {
    COTTON {
        override fun value(): String {
            return "Organic Cotton"
        }
    },
    TENCEL {
        override fun value(): String {
            return "Tencel"
        }
    }
}

interface Value {
    fun value(): Any
}