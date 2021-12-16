<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container" >

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>

		<div class="bg-light p-4 mb-3 rounded">
			<h1>Estas son las atracciones de la Tierra Media</h1>
		</div>

		<c:if test="${user.isAdmin()}">
			<div><
			<div class="mb-3">
				<a href="/turismo-webapp/atraccion/create.do"
					class="btn btn-primary" role="button"> <i class="bi bi-plus-lg"></i>
					Nueva Atracción
				</a>
				  
    </div>
    	<div class="mb-3">
				<a href="/turismo-webapp/promocion/create.do"
					class="btn btn-primary" role="button"> <i class="bi bi-plus-lg"></i>
					Nueva Promocion
				</a>
				  
    </div>
</div>
		
		</c:if>
		
		
		<div class="card-group" id ="grupoPromociones">
			<c:forEach items="${comprables}" var="comprable">
			
							<c:if test="${comprable.esPromocion()}">
							<div class="col">
								<div class="card" style="width: 18rem;">
									<img src="..." class="card-img-top" alt="...">
									<div class="card-body">
										<h5 class="card-title">${comprable.nombre}</h5>
										<p class="card-text">${comprable.descripcion }</p>
									</div>
									<ul class="list-group list-group-flush">
									<c:forEach items="${promocion.getAtracciones}" var="atraccion">
										<li class="list-group-item"> ${atraccion.getNombre}"</li>
										</c:forEach>
									</ul>
									<div class="card-body">
														<c:choose>
								<c:when
									test="${user.canAfford(comprable) && user.canAttend(comprable) && comprable.hayCupo()}">
									<a href="/turismo-webapp/atraccion/buy.do?id=${promocion.id}"
										class="btn btn-success rounded" role="button">Comprar</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-secondary rounded disabled"
										role="button">No se puede comprar</a>
								</c:otherwise>
							</c:choose> 
										
										<c:if test="${user.admin}">
										<a href="/turismo-webapp/atraccion/edit.do?id=${comprable.id}"
											class="btn btn-light rounded-0" role="button"><i
											class="bi bi-pencil-fill"></i></a>
										<a
											href="/turismo-webapp/atraccion/delete.do?id=${comprable.id}"
											class="btn btn-danger rounded" role="button"><i
											class="bi bi-x-circle-fill"></i></a>
									</c:if>
						
								</div>								
</div>
</div>
</c:if>
					
</c:forEach>
							</div>
<div class="card-group">
			<c:forEach items="${comprables}" var="comprable">
			
							<c:if test="${!comprable.esPromocion()}">
							<div class="col">
								<div class="card" style="width: 18rem;">
									<img src="..." class="card-img-top" alt="...">
									<div class="card-body">
										<h5 class="card-title">${comprable.nombre}</h5>
										<p class="card-text">${comprable.descripcion }</p>
									</div>
						
									<div class="card-body">
														<c:choose>
								<c:when
									test="${user.canAfford(comprable) && user.canAttend(comprable) && comprable.hayCupo()}">
									<a href="/turismo-webapp/atraccion/buy.do?id=${atraccion.id}"
										class="btn btn-success rounded" role="button">Comprar</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-secondary rounded disabled"
										role="button">No se puede comprar</a>
								</c:otherwise>
							</c:choose> 
										
										<c:if test="${user.admin}">
										<a href="/turismo-webapp/atraccion/edit.do?id=${comprable.id}"
											class="btn btn-light rounded-0" role="button"><i
											class="bi bi-pencil-fill"></i></a>
										<a
											href="/turismo-webapp/atraccion/delete.do?id=${comprable.id}"
											class="btn btn-danger rounded" role="button"><i
											class="bi bi-x-circle-fill"></i></a>
									</c:if>
						
								</div>								
</div>
</div>
</c:if>
						
									</c:forEach>
							</div>
	</main>

</body>
</html>