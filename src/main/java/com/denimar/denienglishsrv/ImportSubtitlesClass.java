package com.denimar.denienglishsrv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.domain.T08VIS;

public class ImportSubtitlesClass {
	
	public static void main(String[] args) throws IOException {
		String srtStrFileContent = FileUtils.readFileToString(new File("c:\\Denimar\\subtitle.srt"));
		List<T08VIS> subtitlesAdded = addSubtitleFromFileStrContent(2481, srtStrFileContent);
		System.out.println(subtitlesAdded.size());
	}

	private static List<T08VIS> addSubtitleFromFileStrContent(int cdVideo, String strFileContent) {
		String[] strFileContentArray = strFileContent.trim().split("\n");
		
		T08VDO t08vdo = new T08VDO(); //criar aqui
		t08vdo.setCdVideo(cdVideo);
		
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
				
				System.out.println(t08vis);
			}
		}
		
		return null;
	}
	
	private static Double StrTimeToDouble(String prTimeStr) {
		//00:05:22,900
		
		String xTimeStr = prTimeStr;
		Double xHours = Double.valueOf(xTimeStr.substring(0, 2));
		Double xMinutes = Double.valueOf(xTimeStr.substring(3, 5));
		xTimeStr = xTimeStr.substring(6, xTimeStr.length()).replace(",", ".");
		Double xSeconds = Double.valueOf(xTimeStr);		
		
		return (xHours * 3600.0) + (xMinutes * 60.0) + xSeconds;
	}
	
	private static String DoubleToStrTime(double prTimeDouble) {
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
