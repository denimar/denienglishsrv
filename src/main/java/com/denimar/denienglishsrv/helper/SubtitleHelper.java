package com.denimar.denienglishsrv.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.domain.T08VIS;
import com.denimar.denienglishsrv.service.T08VISService;

@Component
public class SubtitleHelper {
	
	@Autowired
	T08VISService t08visService;

	public void addSubtitleFromFileStrContent(T08VDO t08vdo, String strFileContent) {
		String[] strFileContentArray = strFileContent.trim().split("\n");
		
		List<String> subtitlesList = new ArrayList<String>(Arrays.asList(strFileContentArray));
		
		final String searchString = "-->";
		for (int count = 0 ; count < subtitlesList.size() ; count++) {
			String strLine = subtitlesList.get(count);
			
			int pos = strLine.indexOf(searchString);
			if (pos != -1) {
				String startStr = strLine.substring(0, pos - 1).trim();
				String endStr = strLine.substring(pos + 4).trim();
				
				String auxText = "";
				count++;
				if (count < subtitlesList.size()) {
					strLine = subtitlesList.get(count);
					while ((count < subtitlesList.size()) && (!strLine.trim().equals(""))) {
						if (!auxText.equals("")) {
							auxText += "<br>";
						}
						auxText += strLine;
						
						count++;
						if (count >= subtitlesList.size()) {
							break;
						}
						strLine = subtitlesList.get(count);
					}
				}
				
				T08VIS t08vis = new T08VIS();
				t08vis.setDsTexto(auxText);
				t08vis.setNrStart(StrTimeToDouble(startStr));				
				t08vis.setNrEnd(StrTimeToDouble(endStr));
				t08vis.setT08vdo(t08vdo);
				t08visService.save(t08vis);
			}
		}
	}

	private Double StrTimeToDouble(String prTimeStr) {
		//00:05:22,900
		
		String xTimeStr = prTimeStr;
		Double xHours = Double.valueOf(xTimeStr.substring(0, 2));
		Double xMinutes = Double.valueOf(xTimeStr.substring(3, 5));
		xTimeStr = xTimeStr.substring(6, xTimeStr.length()).replace(",", ".");
		Double xSeconds = Double.valueOf(xTimeStr);		
		
		return (xHours * 3600.0) + (xMinutes * 60.0) + xSeconds;
	}
	
	private String DoubleToStrTime(double prTimeDouble) {
		//00:05:22,900

		final int SEGUNDOS_HORA = 3600;		
		final int SEGUNDOS_MINUTO = 60;
		
		double xSegundos = prTimeDouble;
		
		int xHoras = (int) (xSegundos / SEGUNDOS_HORA);
		
		xSegundos = xSegundos % SEGUNDOS_HORA;
		
		int xMinutos = (int) (xSegundos / SEGUNDOS_MINUTO);		

		xSegundos = xSegundos % SEGUNDOS_MINUTO;		
		
		return String.format("%02d", xHoras) + ":" + String.format("%02d", xMinutos) + ":" + String.format("%.3f", xSegundos);
	}	
	
}
