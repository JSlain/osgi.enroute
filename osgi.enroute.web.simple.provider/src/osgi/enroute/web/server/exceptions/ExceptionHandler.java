package osgi.enroute.web.server.exceptions;

import javax.servlet.http.*;

import org.osgi.service.log.*;

public class ExceptionHandler {

	private LogService 				log;

	public ExceptionHandler(LogService log) {
		this.log = log;
	}

	public void handle(HttpServletRequest rq, HttpServletResponse rsp, Exception exception) {
		try {
			try {
				throw exception;
			}
			catch (Redirect302Exception e) {
				rsp.setHeader("Location", e.getPath());
				rsp.sendRedirect(e.getPath());
			}
			catch (NotFound404Exception e ) {
				rsp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			catch (InternalServer500Exception e) {
				log.log(LogService.LOG_ERROR, "Internal webserver error", e);
				rsp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
		catch (Exception ee) {
			log.log(LogService.LOG_ERROR, "Second level or unknown internal webserver error", ee);
		}
	}
}
