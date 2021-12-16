<%@page import="services.AtraccionService"%>
<%@page import="persistence.commons.DAOFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

	
<jsp:include page="/partials/head.jsp"></jsp:include>


<script type="text/javascript">
$(document).ready(function () {
	
	   function generadorDeString(atracciones_promo) {
      	   stringDeAtracciones = atracciones_promo[0];
         for (let i = 1 ;i < atracciones_promo.length; i++){
      	  stringDeAtracciones += "-" + atracciones_promo[i] 
      }
   	  return stringDeAtracciones;
   }
	$('#AXB').on('change', function() {
		$("#descuentoInput").hide();
	})
	$('#ABS').on('change', function() {
		$("#descuentoInput").show();
	})
	$('#POR').on('change', function() {
		$("#descuentoInput").show();
	});
	
 
    	$('#atracciones_checkbox').click(function() {
    		   var atracciones_promo = [];
    		   var stringDeAtracciones = "";
    		   
    	         
      
                
      		   
               $.each($("input:checkbox[name='atracciones_promo']:checked"), function () {
                  atracciones_promo.push($(this).val());
                      
    		});
               console.log($(stringDeAtracciones));
               generadorDeString(atracciones_promo);
               $('#atracciones_promo').value = stringDeAtracciones;
          
              
    		/*  
    		$("#submitPromocion").click(function () {
                   
                   $.ajax({
                  	 type : 'POST',
                  	 data : {atracciones_promo : atracciones_promo},
                  	 url :'/promocion/create.do'
                  
             })  
             
             
            });*/
            
        })

    });




</script>


</head>
<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

		<c:if test="${promocion != null && !promocion.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al crear la atracción.</p>
			</div>
		</c:if>

		<form action="/turismo-webapp/promocion/create.do" method="post">
			<jsp:include page="/views/promocion/form.jsp"></jsp:include>
		</form>
	</main>

</body>
</html>
