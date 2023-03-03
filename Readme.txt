Readme:

David Fonseca Acosta

Mobile Developer Android/iOS(Android, Kotlin test)

Se crea una app de recetas con 3 pantallas, las cuales son:

1.Lista de recetas(home). En esta pantalla se crea una lista con el elemento recyclerview y cardview con los datos correspondientes
de las recetas que se obtienen mediante una api dummy creada en https://www.mockable.io/ dicha lista(json) tiene la información de nombre de receta, origen de la receta y los pasos de preparación de la receta, así como el lugar de origen y las coordenadas del origen de la receta, estos datos se utilizan para mostrar información en todas las pantallas y con estos datos podemos realizar una búsqueda mediante el nombre de la receta o la descripción de la receta.

2.En la pantalla 2 se utiliza una imagen, los pasos de la receta, el origen de la receta y por detrás de esa pantalla se tienen los datos de las coordenadas de la región de origen de dicha receta. En esta pantalla se tiene un botón para ir a la siguiente pantalla donde encontraremos un mapa.

3.En esta pantalla se agrega un mapa con la api de google maps para mostrar el marcador de origen de la receta mediante la latitud y longitud en dicho mapa.

para el proyecto se utilizan las siguientes herramientas:

1.Se utiliza Android Studio
2.Se utiliza Kotlin
3.Se utiliza retrofit 2 para el consumo de servicio.
4.se utiliza https://www.mockable.io/ para crear una api dummy
5.se habilita el elemento viewBinding de jetpack
6.se utilizan coroutines y lifecycle(view model)
7.se utiliza api Google maps con sus marcadores
8.Se utiliza una arquitectura MVVM
9.se utiliza clean architecture

Finalmente se sube la app a mi repositorio personal de Github(publico):

https://github.com/DavidFon1189/RecetaCocina.git


