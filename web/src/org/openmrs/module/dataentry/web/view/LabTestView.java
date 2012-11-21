package org.openmrs.module.dataentry.web.view;

import java.io.OutputStreamWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.View;

public class LabTestView implements View{
	protected final Log log = LogFactory.getLog(getClass());
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public void render(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml");
		OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream());
		
		StringBuffer sb = new StringBuffer();
		sb.append("<response>");
			sb.append("<date>");
			sb.append(model.get("date"));
			sb.append("</date>");
			
			sb.append("<test>");
			sb.append(model.get("test"));
			sb.append("</test>");
			
			sb.append("<result>");
			sb.append(model.get("result"));
			sb.append("</result>");
			sb.append("</response>");
			log.info("building xml response");
			log.info(sb.toString());
		osw.write(sb.toString());
		osw.flush();
		
		
	}

}
