//Creamos un array que almacenará los valores de los input "checked"
var checked = [];
//Recorremos todos los input checkbox con name = Colores y que se encuentren "checked"
$("input[name='atracciones_promo']:checked").each(function ()
{
//Mediante la función push agregamos al arreglo los values de los checkbox
checked.push(($(this).attr("value")));
});
// Utilizamos console.log para ver comprobar que en realidad contiene algo el arreglo
console.log(checked);
