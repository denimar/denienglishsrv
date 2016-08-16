package com.denimar.denienglishsrv.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denimar.denienglishsrv.domain.enums.VIDEO_TYPE_ENUM;
import com.denimar.denienglishsrv.service.T08VDOService;

@Component
public class VideoHelper {
	
	@Autowired
	private T08VDOService t08vdoService;
	@Autowired
	private GeneralHelper generalHelper;	

	public byte[] getVideoImage(VIDEO_TYPE_ENUM tpVideo, String idVideo) throws IOException {
		
		String urlImage = null;
		if (tpVideo == VIDEO_TYPE_ENUM.YOUTUBE) {
			urlImage = "http://img.youtube.com/vi/" + idVideo + "/default.jpg";
		} else if (tpVideo == VIDEO_TYPE_ENUM.GOOGLE_DRIVE) {
			urlImage = "https://docs.google.com/vt?id=" + idVideo;
		}
		
		return generalHelper.downloadFileBitesArray(new URL(urlImage));
	}
	
	public byte[] getBytesFromUriImagem(String uriImagemBase64) throws IOException {
	    String uriImagem = uriImagemBase64;
	    uriImagem = uriImagem.substring("data:image/png;base64,".length());
	    Base64 decoder = new Base64();	    
	    return decoder.decode(uriImagem);  
	}
	
	public String getUtlPoster(VIDEO_TYPE_ENUM videoType, String videoId) throws Exception {
		if (videoType == VIDEO_TYPE_ENUM.YOUTUBE) {
			return "https://img.youtube.com/vi/" + videoId + "/default.jpg";
		} else {
			File tempDir = new File("temp");
			tempDir.mkdirs();
			String link1 = "https://docs.google.com/file/d/" + videoId + "/edit?pref=2&pli=1";
			File targetFile = new File(tempDir, videoId + ".html");
			downloadFile(link1, targetFile);
			
			String fileContent = FileUtils.readFileToString(targetFile, "UTF-8");
			
			int pos = fileContent.indexOf("property=\"og:image\"");
			pos = fileContent.indexOf("content", pos);
			
			int posStart = pos + 9;
			int posEnd = fileContent.indexOf("\">", posStart);
			
			return fileContent.substring(posStart, posEnd);
		}	
	}
	
	public byte[] getBytesArrayFromURL(String location) throws Exception {
	  URL url = new URL(location);
	  InputStream is = null;
	  byte[] bytes = null;
	  try {
	    is = url.openStream ();
	    bytes = IOUtils.toByteArray(is);
	  } catch (IOException e) {
	    //handle errors
	  }
	  finally {
	    if (is != null) is.close();
	  }
	  return bytes;
	}
	
    /**
     * Downloads a file from a URL
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    public void downloadFile(String fileURL, File targetFile) throws IOException {
    	final int BUFFER_SIZE = 4096;
    	URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
 
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();
 
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + targetFile.getName());
 
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
             
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(targetFile);
 
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            outputStream.close();
            inputStream.close();
 
            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }	
	
	

}
