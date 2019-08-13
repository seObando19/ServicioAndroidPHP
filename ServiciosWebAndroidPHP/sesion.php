<?php 
	include 'conexion.php';
	//$cnx =  mysqli_connect("localhost","root","","bdusuarios");
	if (isset($_REQUEST['usr']) && isset($_REQUEST['clave']))
	{
		$usr=$_REQUEST['usr'];
		$clave=$_REQUEST['clave'];
		$registros=$cnx->query("select usr,correo,clave,nombre from usuario where usr = '$usr' and clave = '$clave'");		
		$json = array();
		foreach ($registros as $fila) 
		{
			$json['datos'][]=$fila;
		}
		//pasar los datos del array a JSON con informacion o vacío
		echo json_encode($json);
	}
	else
	{
		echo "El usr y la clave son obligatorios";
	}

 ?>