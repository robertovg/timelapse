package timeLapse.controller.interceptors.inyector;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;
import org.springframework.stereotype.Service;

import timeLapse.util.annotations.SessionValue;
import timeLapse.util.constantes.Globales;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
@Service("inyectorAtributosSession")
public class InyectorAtributosSession extends AbstractInterceptor implements Globales,StrutsStatics {

    public InyectorAtributosSession () {
        super();
    }

    
    @Override
	public String intercept(ActionInvocation invocation)
            throws Exception {

    	final ActionContext context = invocation.getInvocationContext ();
  	    HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
  	    HttpSession session =  request.getSession();

        
        Object action = invocation.getAction();

        for (Method m : action.getClass().getMethods()) {
            if (m.isAnnotationPresent(SessionValue.class)) {
                 if (m.getName().startsWith("set")) {
                     SessionValue ann = m.getAnnotation(SessionValue.class);
                     String id = ann.id();
                     Object obj = null;
                     
                	 obj = session.getAttribute(id);

                     m.invoke(action, obj);
                 }
            }
        }

        return invocation.invoke();
    }
}
