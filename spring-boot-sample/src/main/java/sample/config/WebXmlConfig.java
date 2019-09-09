package sample.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import java.util.EnumSet;

/**
 * Web.xml en Java Config
 * 
 * @author A683280
 *
 */
@Configuration
public class WebXmlConfig implements ServletContextInitializer {

	/**/
	public static final String ERRORFILTER = "ErrorFilter";

	@Override
	public void onStartup(ServletContext servletContext) {
		EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD,
				DispatcherType.ASYNC, DispatcherType.ERROR);

	}



}
