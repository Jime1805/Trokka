package com.iticbcn.trokka

data class UsageInfo (
    var horasUso: Double = 0.0,
    var countVisitasMapa: Int = 0,
    var countEntradesApp: Int = 0,  // Veces que entra a la app
    var countCrearObjeto: Int = 0,
    var countFavoritos: Int = 0
)