package timeLapse.controller.interceptors.jsonResul;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * This class provide a wrapper for an http response
 * 
 * @author hunk
 */
public class ResponseWrapper {
	private String contentType = "application/json";
	private String characterEncoding = "utf-8";
	private boolean gzip;
	private boolean noCache;
	private int statusCode = 200;
//	private static final Log log = LogFactory.getLog(ResponseWrapper.class);

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public void setGzip(boolean gzip) {
		this.gzip = gzip;
	}

	public void setNoCache(boolean noCache) {
		this.noCache = noCache;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void writeResult(HttpServletRequest request,
			HttpServletResponse response, String result) throws IOException {
		response.setContentType(contentType);
		response.setStatus(statusCode);
		response.setCharacterEncoding(characterEncoding);

		if (noCache) {
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
			response.setHeader("Pragma", "No-cache");
		}

		if (gzip && isGzipInRequest(request)) {
			response.addHeader("Content-Encoding", "gzip");
			GZIPOutputStream out = null;
			try {
				out = new GZIPOutputStream(response.getOutputStream());
				out.write(result.getBytes(characterEncoding));
			} finally {
				if (out != null) {
					out.finish();
					out.close();
				}
			}
		} else {
			int contentLength = result.getBytes(characterEncoding).length;
			response.setContentLength(contentLength);
			PrintWriter out = response.getWriter();
			out.write(result);
		}
	}

	/**
	 * check whether gzip encoding is accepted by the browser again borrowed
	 * from struts json plugin
	 * 
	 * @param request
	 * @return
	 */
	private boolean isGzipInRequest(HttpServletRequest request) {
		String header = request.getHeader("Accept-Encoding");
		return header != null && header.indexOf("gzip") >= 0;
	}
}
