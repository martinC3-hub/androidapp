package com.example.login

import com.google.firebase.firestore.FirebaseFirestore

class BusquedaProvider {
    companion object{
            val busquedaFirst = mutableListOf<Busqueda>()

            fun cargarBusquedasF(onComplete: (List<Busqueda>) -> Unit, onError: (Exception) -> Unit) {
                val db = FirebaseFirestore.getInstance()
                db.collection("busquedas")
                    .get()
                    .addOnSuccessListener { result ->
                        busquedaFirst.clear()
                        for (document in result) {
                            val busqueda = document.toObject(Busqueda::class.java)
                            busquedaFirst.add(busqueda)
                        }
                        onComplete(busquedaFirst)
                    }
                    .addOnFailureListener { exception ->
                        onError(exception)
                    }
            }
    }
}

    /*

                Busqueda(
                "",
                "",
                ""
            ),
            Busqueda(
                "",
                "",
                ""
            ),
            Busqueda(
                "Aprende a trabajar en equipo",
                "Softskills",
                ""
            ),
            Busqueda(
                "¿Me perdonas? Si te he fallado te pido perdon de la única forma que estoy abriendo las puertas de mi corazon para cuando desidas volver porque nunca avra nadie ke pueda llenar el vacio que dejastes en mi me has cambiad",
                "Softskills",
                ""
            ),
            Busqueda(
                "Grande Chivas",
                "Fubol",
                "https://www.futboltotal.com.mx/wp-content/uploads/2019/01/memes-18-150x150.jpg"
            ),
     */