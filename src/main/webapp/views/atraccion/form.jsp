<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="nombre" name="nombre" required
			value="${atraccion.nombre}">
	</div>
	<div class="mb-3">
		<label for="descripcion" class="col-form-label">Descripcion:</label> <input
			type="text" class="form-control" id="descripcion" name="descripcion"
			required value="${atraccion.nombre}">
	</div>
	<div class="mb-3">
		<label for="costo"
			class='col-form-label ${atraccion.errors.get("costo") != null ? "is-invalid" : "" }'>Costo:</label>
		<input class="form-control" type="number" id="costo" name="costo"
			required value="${atraccion.costo}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("costo")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="duracion"
			class='col-form-label ${atraccion.errors.get("duracion") != null ? "is-invalid" : "" }'>Duración:</label>
		<input class="form-control" type="number" id="duracion"
			name="duracion" required value="${atraccion.duracion}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("duracion")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="cupoMaximo"
			class='col-form-label ${atraccion.errors.get("cupoMaximo") != null ? "is-invalid" : "" }'>Cupo
			máximo:</label> <input class="form-control" type="number" id="cupoMaximo"
			name="cupoMaximo" required value="${atraccion.cupoMaximo}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("cupoMaximo")}'></c:out>
		</div>
		<p>Selecciona el tipo de atraccion:</p>

		<div>
			<input type="radio" id="Aventura" name="tipo_atracciones" value="Aventura" checked>
			<label for="Aventura">Aventura</label>
		</div>

		<div>
			<input type="radio" id="Degustacion" name="tipo_atracciones" value="Degustacion"> <label
				for="Degustacion">Degustación</label>
		</div>

		<div>
			<input type="radio" id="Paisaje" name="tipo_atracciones" value="Paisaje"> <label
				for="Paisaje">Paisaje</label>
		</div>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
