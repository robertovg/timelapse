package timeLapse.controller.interceptors.jsonResul;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;

import flexjson.JSONSerializer;

/**
 * A json result type for struts2
 * 
 * @author hunk[LRGCC(a) yahoo(dot)com(dot)cn]
 */
@SuppressWarnings("serial")
public class JSONResult implements Result {

	private String target;
	private List<String> patterns;
	private boolean prettyPrint;
	private String rootName;
	private String callbackParameter;
	private boolean deepSerialize;
	private boolean prefix;

	private Pattern callbackFunctionPattern;

	public void setCallbackParameter(String callbackParameter) {
		this.callbackParameter = callbackParameter.trim();
		if (callbackFunctionPattern == null) {
			callbackFunctionPattern = Pattern.compile("[a-zA-Z_$0-9]*");
		}
	}

	public void setPrefix(boolean prefix) {
		this.prefix = prefix;
	}

	private static final Log log = LogFactory.getLog(JSONResult.class);
	private ResponseWrapper out = new ResponseWrapper();
	private JSONSerializer serializer;

	public void setPrettyPrint(boolean prettyPrint) {
		this.prettyPrint = prettyPrint;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public void setDeepSerialize(boolean deepSerialize) {
		this.deepSerialize = deepSerialize;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public ResponseWrapper getOut() {
		return out;
	}

	public void setOut(ResponseWrapper out) {
		this.out = out;
	}

	public void setPatterns(String pattern) {
		String[] elements = pattern.split("\\s*,\\s*");
		this.patterns = new ArrayList<String>();
		for (String element : elements) {
			if (element.length() != 0) {
				this.patterns.add(element);
			}
		}
	}

	public void execute(ActionInvocation invocation) throws Exception {
		if (prettyPrint && deepSerialize) {
			log.warn("prettyPrint can't be used with deepSerialize, ignored.");
		}

		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletResponse response = (HttpServletResponse) actionContext
				.get(StrutsStatics.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);

		if (serializer == null) {
			initSerializer();
		}

		Object targetObject = extractTargetObject(invocation);

		if (log.isTraceEnabled()) {
			log.trace(String.format("rootName is set to %s", rootName));
			log.trace(String.format("prettyPrint is set to %b", prettyPrint));
			log.trace(String
					.format("deepSerialize is set to %b", deepSerialize));
		}

		StringBuilder result = new StringBuilder();
		boolean appendCallback = false;
		if (callbackParameter != null) {
			String callback = request.getParameter(callbackParameter);
			if (callback == null) {
				log.debug("callback parameter is not set");
			} else if (callbackFunctionPattern.matcher(callback).matches()) {
				result.append(callback);
				result.append('(');
				appendCallback = true;
			} else {
				log
						.debug("The callback function name contains invalid character, ignored");
			}
		}
		if (prefix) {
			result.append("{}&&");
		}

		if (deepSerialize) {
			if (rootName != null) {
				result.append(serializer.deepSerialize(rootName, targetObject));
			} else {
				result.append(serializer.deepSerialize(targetObject));
			}
		} else if (!prettyPrint) {
			if (rootName != null) {
				result.append(serializer.serialize(rootName, targetObject));
			} else {
				result.append(serializer.serialize(targetObject));
			}
		} else {
			if (rootName != null) {
				result.append(serializer.prettyPrint(rootName, targetObject));
			} else {
				result.append(serializer.prettyPrint(targetObject));
			}
		}
		if (appendCallback) {
			result.append(");");
		}
		if (log.isTraceEnabled()) {
			log.trace("result: " + result.toString());
		}

		out.writeResult(request, response, result.toString());
	}

	/**
	 * find the target object according the target property(parameter) if not
	 * set, the action will be used.
	 * 
	 * @param invocation
	 * @return
	 */
	private Object extractTargetObject(ActionInvocation invocation) {
		Object targetObject;
		if (this.target != null) {
			ValueStack stack = invocation.getStack();
			targetObject = stack.findValue(this.target);
			if (log.isTraceEnabled()) {
				log.trace(String.format("Evaluate serializer target %s to %s.",
						target, targetObject));
			}
		} else {
			targetObject = invocation.getAction();
			if (log.isTraceEnabled()) {
				log.trace("Using action instance as serializer target.");
			}
		}
		return targetObject;
	}

	private void initSerializer() {
		this.serializer = new JSONSerializer();
		if (patterns != null) {
			for (String pattern : patterns) {
				log.debug("processing pattern: " + pattern);
				char flag = pattern.charAt(0);
				pattern = pattern.substring(1);

				switch (flag) {
				case '-':
					serializer.exclude(pattern);
					break;
				case '+':
					serializer.include(pattern);
					break;
				default:
					throw new RuntimeException(
							"Pattern must start with '-' or '+'");
				}
			}
		}
	}
}
