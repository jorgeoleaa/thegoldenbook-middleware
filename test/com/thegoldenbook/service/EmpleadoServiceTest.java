package com.thegoldenbook.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Address;
import com.thegoldenbook.model.Employee;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.EmpleadoService;
import com.thegoldenbook.service.impl.EmpleadoServiceImpl;

public class EmpleadoServiceTest {

	private EmpleadoService empleadoService = null;
	private static Logger logger = LogManager.getLogger(EmpleadoServiceTest.class);

	public EmpleadoServiceTest() {
		empleadoService = new EmpleadoServiceImpl();
	}
	
	public void testAutenticacionOK() throws Exception{

		logger.trace("Testing Autenticacion de usuario y password correctas...");


			Employee e = empleadoService.autenticar(8l, "hola2");

			if (e!=null) {
				logger.trace("Autenticación correcta. Todo OK!");
				logger.trace(e);
			} else {
				logger.trace("Fallo en el método de autenticación con usuario y password correctos.");
			}
		
	}

	public void testAutenticacionUsuarioNoExistente() throws Exception{

		logger.traceEntry("Testing Autenticacion de usuario inexistente...");

		try {
			Employee empleado = empleadoService.autenticar(
					11l, "contrasena");
			if (empleado!=null) {
				logger.trace("Fallo en el método de autentication");			
			} else {
				logger.trace("Autenticacion de usuario no existente correcta! (Usuario no encontrado!)");
			}

		} catch(DataException e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void testAutenticacionPasswordIncorrecta() throws Exception{

		logger.traceEntry("Testing autenticación usuario existente pero contraseña incorrecta");

		
			Employee empleado = empleadoService.autenticar(8l, "abc123.");

			if(empleado!=null) {
				logger.trace("Fallo en el proceso de autenticación");
			}else {
				logger.trace("Autentificación con password incorrecta, correcta!");
			}	
		
	}
	public void testFindAll() throws Exception{
		logger.traceEntry("Testing FindAll...");
		
		Results<Employee> empleados = empleadoService.findAll(1, 500);
		
		for(Employee e : empleados.getPage()) {
			logger.info(e);
		}
	}
	
	
	public void testFindById () throws Exception{
		logger.traceEntry("Testing FindById...");
		
		Employee e = empleadoService.findBy(2l);
		
		if(e != null) {
			logger.info(e);
		}else {
			logger.info("No se han encontrado resultados");
		}
		
	}

	public void testRegistrar() throws Exception{
		logger.traceEntry("Testing create...");
		
		
			Address d = new Address();
			d.setNombreVia("Rúa da Concepción");
			d.setDirVia("nº4 piso 3ºB");
			d.setLocalidadId(6);		

			Employee e = new Employee();
			e.setNombre("Jorge");
			e.setApellido1("Olea");
			e.setApellido2("Casanova");
			e.setDniNie("99888777M");
			e.setEmail("joroleacasanova@gmail.com");
			e.setTelefono("654321123");
			e.setPassword("abccccccccc444");
			e.setTipo_empleado_id(3);
			e.setDireccion(d);

			empleadoService.registrar(e);
				
			if(e.getId() != null) {
				logger.info("Empleado: "+e.getId()+" creado correctamente.");
			}else {
				logger.info("El empleado no se ha creado correctamente");
			}
			
		
	}

	public void testDelete() throws Exception{
		
		logger.traceEntry("Testing delete...");
		
			Employee e = new Employee();
			e.setId(15l);
			if(empleadoService.delete(e.getId())) {
				logger.trace("Eliminado el empleado: "+e);
			}else {
				logger.trace("No se ha eliminado el empleado");
			}
	}

	public void testUpdate() throws Exception{
		logger.traceEntry("Testing update...");
		
			Employee e = empleadoService.findBy(15l);
			e.setNombre("Jorge");
			e.setApellido1("dsfdsdf");
			e.setTipo_empleado_id(2);
			e.setDniNie("dsfsdsdfs");
			if(empleadoService.update(e)) {
				logger.trace("Empleado "+e+ " actualizado correctamente");
			}else {
				logger.trace("No se han actualizado los datos del empleado proporcionado");
			}
	}
	
	public void testUpdatePassword() throws Exception{
		logger.traceEntry("Testing updatePassword...");
		String password = "444444444.";
		Long id = 7l;
		if (empleadoService.updatePassword(password, id)) {
			logger.info("Su contraseña ha sido actualizada correctamente");
		}else{
			logger.info("Su contraseña no ha sido actualizada");
		}
			
	}

	public static void main(String [] args) throws Exception{
		EmpleadoServiceTest test = new EmpleadoServiceTest();
		//test.testFindById();
		//test.testFindAll();
		//test.testRegistrar();
		//test.testAutenticacionOK();
		//test.testAutenticacionUsuarioNoExistente();
		//test.testAutenticacionPasswordIncorrecta();
		//test.testDelete();
		//test.testUpdate();
		test.testUpdatePassword();
		
	}

}
