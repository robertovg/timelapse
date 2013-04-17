package timeLapse.controller.interceptors.logger;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;

@Service("springLoggerInterceptor")
public class SpringLoggerInterceptor implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice{

	private static final Log logger = LogFactory.getLog(SpringLoggerInterceptor.class);
	private long startTime;  
	
	
	 public void before(Method method, Object[] args, Object target) throws Throwable {
		 startTime = System.currentTimeMillis();
		 
		  // generamos la lista de argumentos que recibe el metodo separados por una coma  
          String arguments = new String();              
          for (int i = 0; i < args.length; i++) {  
              arguments += args[i] + " ,";               
          }  
            
          // el metodo recibe al menos un argumento quitamos el espacio y la coma del final  
          if (arguments.length() > 0) {  
              arguments = arguments.substring(0, arguments.length() - 2);  
          }  
         
		 logger.debug(method.getDeclaringClass().getName()
			       + "!" + method.getName() + "("+arguments+ ")" + "...Inicio");
	 }
	
	 public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
	    logger.debug(method.getDeclaringClass().getName()
	       + "!" + method.getName() + "... Salida(" + (System.currentTimeMillis() - startTime) +"ms)");
	 }
	 
	 public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
		 logger.error(method.getDeclaringClass().getName()
			       + "!" + method.getName() + "... (" + (System.currentTimeMillis() - startTime) +"ms). Excepción: " + ex);
		 
	 }

	 

	 
	 
	
}
