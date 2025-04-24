package org.sam.springboot.form.app.interceptors;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("POST")) {
			return true;
		}
		
		if(handler instanceof HandlerMethod) {
			HandlerMethod metodo = (HandlerMethod) handler;
			logger.info("Es un método del Controlador: "+ metodo.getMethod().getName());
		}
		
		logger.info("TiempoTranscurridoInterceptor: preHandle() entrando...");
		logger.info("Interceptando: "+ handler);
		long tiempoInicio = System.currentTimeMillis();
		
		request.setAttribute("tiempoInicio", tiempoInicio);
		
		Random random =  new Random();
		Integer demora = random.nextInt(100);
		Thread.sleep(demora);
		
		//response.sendRedirect(request.getContextPath().concat("/login")); // redirección en caso de fallo
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if(request.getMethod().equalsIgnoreCase("POST")) {
			return;
		}
			long tiempoFin = System.currentTimeMillis();
			long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
			long tiempoTranscurrido = tiempoFin - tiempoInicio;
			
			if(handler instanceof HandlerMethod && modelAndView != null) {
				modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
			}
			
			logger.info("Tiempo Transcurrido: " + tiempoTranscurrido + " milisegundos");
			logger.info("TiempoTranscurridoInterceptor: postHandle() saliendo...");
		
	}

	
	
}
