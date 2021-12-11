<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="name" class="col-form-label">Username:</label> <input
		type="text" class="form-control" id="name" name="username"
		required value="${tmp_user.username}">
</div>
<div class="mb-3">
	<label for="coins"
		class='col-form-label ${tmp_user.errors.get("presupuesto") != null ? "is-invalid" : "" }'>Monedas:</label>
	<input class="form-control" type="number" id="presupuesto" name="presupuesto"
		required value="${tmp_user.presupuesto}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_user.errors.get("presupuesto")}'></c:out>
	</div>
</div>

<div class="mb-3">
	<label for="time"
		class='col-form-label ${tmp_user.errors.get("disponibilidad") != null ? "is-invalid" : "" }'>Tiempo:</label>
	<input class="form-control" type="number" id="disponibilidad" name="disponibilidad"
		required value="${tmp_user.disponibilidad}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_user.errors.get("disponibilidad")}'></c:out>
	</div>
</div>

<div class="mb-3">
	<label for="password"
		class='col-form-label ${tmp_user.errors.get("password") != null ? "is-invalid" : "" }'>Contraseña:</label>
	<input class="form-control" id="password" name="password"
		required value="${tmp_user.password}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_user.errors.get("password")}'></c:out>
	</div>
</div>

<div class="form-check">
  <input class="form-check-input" type="radio" name="preferencia" id="preferencia"checked required value= "Aventura">
  <label for ="preferencia" class="form-check-label">
    Aventura
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="preferencia" id="preferencia" required value= "Degustacion">
  <label for="preferencia" class="form-check-label">
    Degustacion
  </label>
</div>

<div class="form-check">
  <input class="form-check-input" type="radio" name="preferencia" id="preferencia" required value= "Paisajismo">
  <label for= "preferencia" class="form-check-label">
    Paisajismo
  </label>
</div>

<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
