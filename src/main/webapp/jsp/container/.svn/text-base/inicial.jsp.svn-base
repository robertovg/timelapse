<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="es_ES" scope="session"/>
<c:if test="${param['lang'] != null}">
<fmt:setLocale value="${param['lang']}" scope="session"/>
</c:if>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
		<!-- Icono -->
		<link rel="shortcut icon" href="../images/timelapse-ico.png">
		<!-- Js de dojo, inicializandolo. -->
		<script type="text/javascript" src="../dojo/dojo.js.uncompressed.js"
  		  djConfig="parseOnLoad:false, isDebug:true, usePlainJson:true, locale:'es_es'">
  		</script>
 		<script type="text/javascript">
    
    	</script>
		<!-- Las css de dojo, con el tema Nihilo -->			
		<link rel="stylesheet" type="text/css" href="../dijit/themes/nihilo/nihilo.css" />
		<link rel="stylesheet" type="text/css" href="../dojox/grid/resources/Grid.css" />
		<link rel="stylesheet" type="text/css" href="../dojox/grid/resources/nihiloGrid.css" />
		<link rel="stylesheet" type="text/css" href="../dojo/resources/dojo.css" />
		<link rel="stylesheet" type="text/css" href="../dojox/widget/Toaster/Toaster.css"/>
		<link rel="stylesheet" type="text/css" href="../dojox/widget/Calendar/Calendar.css"/>
		<link rel="stylesheet" type="text/css" href="../js/timeLapse/widget/templates/Calendar.css"/>
		
		<!-- Nuestras CSS -->
		<link 	rel="stylesheet" type="text/css" href="../css/estilo.css" />
		<link rel="stylesheet" type="text/css" href="../css/iconos.css" />
		<!--[if IE]>
		<link 	rel="stylesheet" type="text/css" href="../css/estiloIE.css" />
		<![endif]-->

  		<!-- Nuestro JS -->
		<script type="text/javascript" src="../js/timeLapse/container/init.js"></script>
		<script type="text/javascript">
    
    	</script>
   		
    			
		<title><fmt:message key="timeLapse.jsp.title"/></title>		
		
	</head>	
	
	<body class="nihilo">
		<noscript><p></p><fmt:message key="timeLapse.jsp.noscript"/><p></p></noscript>
		<div id="preloader" style="inline"></div>
		   <div dojoType="dojox.widget.Toaster" id="toast1" 
				  positionDirection="tr-left"				  
				  messageTopic="mensajeRespuesta"></div>
		<div id="marco">
		 <b class="divBlancoTodo">
			  <b class="divBlancoTodo1"><b></b></b>
			  <b class="divBlancoTodo2"><b></b></b>
			  <b class="divBlancoTodo3"></b>
			  <b class="divBlancoTodo4"></b>
			  <b class="divBlancoTodo5"></b></b>
		<div dojoType="dijit.layout.BorderContainer"  design="headline" id="contenedor">
			
			<div dojoType="dijit.layout.ContentPane" region="top" id="cabContenedor">
				<img id="logo" src="../images/logo.png"/>
				<div id="iconosSuperiores">
				<span id="inicio" ></span>
				<span id="ayuda" ></span>
				<span id="salir" ></span>
				</div>
				<div id="cabecera"></div>				
			</div>
			
			<div dojoType="dijit.layout.ContentPane" region="left" id="cabMenuLateral" splitter="true">
			   	
		    </div>
		    
		    <div dojoType="dijit.layout.ContentPane" region="center" id="cabPrincipal"> 
		        
			   <div id="principal" dojoType="dijit.layout.ContentPane">
			   
			   
			   </div>     
	                
               <div id="popUpContentPane" dojoType="dijit.layout.ContentPane"></div> 
		    </div>
		    
			
		    <div dojoType="dijit.layout.ContentPane" region="bottom" id="pie">
		        
		        <input type="hidden" name="contadorSemaforo" id="contadorSemaforo" dojoType="dijit.form.TextBox" value="0"/>
       			<div dojoType="dijit.ProgressBar" 
        			id="progress" indeterminate="true"></div>
		        <span id="tituloPie"><span id="usuarioRol"> Nombre Usuario-Perfil.</span> timeLapse 1.0</span>
		    </div>
					
		</div>
		<b class="divBlancoTodo">
		  <b class="divBlancoTodo5"></b>
		  <b class=divBlancoTodo4></b>
		  <b class="divBlancoTodo3"></b>
		  <b class="divBlancoTodo2"><b></b></b>
		  <b class="divBlancoTodo1"><b></b></b></b>	
		  
				
		</div>
	</body>	
	
</html>
