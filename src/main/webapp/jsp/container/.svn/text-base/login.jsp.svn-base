<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="es_ES" scope="session"/>
<c:if test="${param['lang'] != null}">
<fmt:setLocale value="${param['lang']}" scope="session"/>
</c:if>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<title><fmt:message key="timeLapse.jsp.title" /></title>
    <!-- Icono -->
    <link rel="shortcut icon" href="../images/timelapse-ico.png">
    
    <!-- Js de dojo, inicializandolo. -->
    <script type="text/javascript" src="../dojo/dojo.js.uncompressed.js" 
    	djConfig="parseOnLoad:false, isDebug:true, usePlainJson:true, locale:'es_es'"/>
    
    <script type="text/javascript">
    
    </script>
    <!-- Las css de dojo, con el tema Nihilo -->
    <link rel="stylesheet" type="text/css" href="../dijit/themes/nihilo/nihilo.css" />    
    <link rel="stylesheet" type="text/css" href="../dojo/resources/dojo.css" />
    <link rel="stylesheet" type="text/css" href="../dojox/widget/Toaster/Toaster.css"/>
    
    <!-- Nuestras CSS -->
    <link rel="stylesheet" type="text/css" href="../css/estilo.css" />
    
    <!-- Nuestro JS -->
    <script type="text/javascript" src="../js/timeLapse/container/login.js"/>
    <script type="text/javascript">
    
    </script>
   	
     
</head>
<body  class="nihilo" >
	<div id="loginBody">
	<noscript><p></p><fmt:message key="timeLapse.jsp.noscript"/><p></p></noscript>
		<form onsubmit="return false" dojoType="dijit.form.Form" id="myForm" 
			encType="multipart/form-data" action="" method="post" >
	    	   <div dojoType="dojox.widget.Toaster" id="toast1" 
				  positionDirection="tr-left"				  
				  messageTopic="mensajeRespuesta"></div>
	    			
	   		<div id="logotipoLogin">        	
			<img src="/timeLapse/images/logo.png"/>
			</div>
			<div id="preloader" style="inline"></div>
			
			<div id="cuerpoPrincipal" style="display:none;">
			  <b class="divRedondo300">
			  <b class="divRedondo1"><b></b></b>
			  <b class="divRedondo2"><b></b></b>
			  <b class="divRedondo3"></b>
			  <b class="divRedondo4"></b>
			  <b class="divRedondo5"></b></b>
			
			  <div class="divRedondofg" id="loginPane">

				
				<div class="elemFormulario">
				<span class="cajaTexto blanco">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.container.login.inputNombreUsuario" /></span>
				</span>	
				<span class="cajaInput">
					<input type="text" 
						id="usuario"
						name="usuario"
						dojoType="dijit.form.ValidationTextBox"					
						required="true"  
						promptMessage="<fmt:message key="timeLapse.jsp.container.login.tooltipNombreUsuario"/>"
						trim="true"
						type="text"
						maxLength="50"
						class="textInput"
						/>
					</span>
				</div>
				
				<div class="elemFormulario">
				<span class="cajaTexto blanco">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.container.login.inputPassword" /></span>
				</span>
				<span class="cajaInput">
					<input 
						id="password" 
						name="password" 
						dojoType="dijit.form.ValidationTextBox"					
						required="true"  
						promptMessage="<fmt:message key="timeLapse.jsp.container.login.tooltipPassword"/>"
						trim="true"					
						type="password"
						maxLength="100"
						class="textInput"										
						/>
					</span>
				</div>
				
				<div class="elemFormulario centrado">
				
					<span>
					
						<input id="recordarme" 
						dojoType="dijit.form.CheckBox"					  
						name="recordarme" value="off" />
						<span class="blanco">
							<span class="labelTexto"><fmt:message key="timeLapse.jsp.container.login.inputRecordarme" /></span>
						</span>
					</span>
				</div>
				
				
		        <div class="contenedorBotones">
					<div dojoType="dijit.form.Button" id="botonEntrar" label="<fmt:message key="timeLapse.jsp.botonEntrar"/>" class="boton"></div>
					<div dojoType="dijit.form.Button" id="botonRegistrar" label="<fmt:message key="timeLapse.jsp.botonRegistrar"/>" class="boton"></div>
				</div>
				<input type="hidden" name="contadorSemaforo" id="contadorSemaforo" dojoType="dijit.form.TextBox" value="0"/>
				<div dojoType="dijit.ProgressBar" 
        			id="progress" indeterminate="true"></div>
			 </div>

		  <b class="divRedondo300">
		  <b class="divRedondo5"></b>
		  <b class="divRedondo4"></b>
		  <b class="divRedondo3"></b>
		  <b class="divRedondo2"><b></b></b>
		  <b class="divRedondo1"><b></b></b></b>
		  <div id="popUpContentPane" dojoType="dijit.layout.ContentPane"></div>
		  
		</div>

    	</form>
    </div>
</body>
</html>
