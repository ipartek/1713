<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>
    
   	
   	
   	<%@include file="includes/mensaje.jsp"%>
   	
   	<div class="d-flex justify-content-center">
   	
	   	<form action="login" method="post" class="">
	   		<div class="form-group">
	   			<h1>Login</h1>
	   		</div>
	   		<div class="form-group">
	   			<input type="text" name="nombre" autofocus placeholder="Tu Nombre Usuario" class="form-control">
	   		</div>
	   		<div class="form-group">
	   			<input type="password" name="contrasenya" placeholder="Contraseņa" class="form-control">
	   		</div>
	   		<input type="submit" value="Entrar" class="btn btn-block btn-primary">
	   	
	   	</form>
   	
   	</div>
    	    	
<%@include file="includes/footer.jsp"%>   