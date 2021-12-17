<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
<div class="mb-3">
   <a class="btn btn-primary" href="/turismo-webapp/promocion/create.do?tipo_atracciones=Aventura">Promocion de Aventura</a>
      <a class="btn btn-primary" href="/turismo-webapp/promocion/create.do?tipo_atracciones=Degustacion">Promocion de Degustacion</a>
      <a class="btn btn-primary" href="/turismo-webapp/promocion/create.do?tipo=Paisaje">Promocion de paisaje</a>
</div>
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="nombre" name="nombre"
			required value="${promocion.nombre}">
	</div>
		<div class="mb-3">
	<label for="descripcion" class="col-form-label">Descripcion:</label> <input
			type="text" class="form-control" id="descripcion" name="descripcion"
			required value="${promocion.descripcion}">
	</div>
	<div class="mb-3" id= "descuentoInput">
		<label for="descuento"
			class='col-form-label ${promocion.errors.get("descuento") != null ? "is-invalid" : "" }'>Descuento:</label>
		<input class="form-control" type="number" name="descuentoInput"
			required value="${promocion.descuento}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("descuento")}'></c:out>
		</div>
			<div class="mb-3">
		<input type="text" name="sarasa" id="costoConDescuento" value="saras" disabled>
	</div>
</div>

		<div class="mb-3">
	<div class="form-check">
  <input class="form-check-input" type="radio" name="tipo_promociones" id="ABS" value ="ABS" checked>
  <label class="form-check-label" for="flexRadioDefault1">
   	Promocion Absoluta
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="tipo_promociones" id="AXB" value ="AXB">
  <label class="form-check-label" for="flexRadioDefault2">
   Promocion AxB
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="tipo_promociones" id="POR" value ="POR">
  <label class="form-check-label" for="flexRadioDefault3">
    Promocion Porcentual
  </label>
</div>
	</div>
</div>

<c:forEach items = "${atracciones}" var ="atraccion" >
<div class="mb-3">
<div class="form-check">
  <input class="form-check-input" type="checkbox" value="${atraccion.id}" id="atracciones_checkbox" name= "atracciones_checkbox">
  <label class="form-check-label" for="flexCheckDefault">
    ${atraccion.nombre}
  </label>
</div>

</div></c:forEach>

		<input class="form-control invisible" type="text" name="atracciones_promo" id="atracciones_promo" value = "${promocion.atracciones_promo}"></input>
			
<div>
	<button type="button" id="cargarAtracciones" class="btn btn-primary">Confirmar Atracciones</button>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>