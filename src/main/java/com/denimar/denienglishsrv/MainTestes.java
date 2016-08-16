package com.denimar.denienglishsrv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.denimar.denienglishsrv.AppManagedDownload.VGetStatus;
import com.github.axet.vget.VGet;
import com.github.axet.vget.info.VGetParser;
import com.github.axet.vget.info.VideoFileInfo;
import com.github.axet.vget.info.VideoInfo;
import com.github.axet.wget.info.ex.DownloadInterruptedError;

public class MainTestes {

	public static void main(String[] args) throws Exception {
		//String link = "https://docs.google.com/vt?id=0B6WapjXIIlDGTjBEcnpzd09Mdkk";		
		//getPosterFromGoogleDrive(id)
		
	}
	
	private static byte[] getPosterFromGoogleDrive(String videoId) throws Exception {
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
		
		String imageLink = fileContent.substring(posStart, posEnd);
		return fetchRemoteFile(imageLink);
	}
	
	private static byte[] fetchRemoteFile(String location) throws Exception {
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
	
	static class DownloadYoutubeThread extends Thread {
		VGet vGetDownload; 
		
		public DownloadYoutubeThread(VGet vGet) {
			this.vGetDownload = vGet;
		}
	 
		public void run() {
			this.vGetDownload.download();
		}
	}	
	
	public static void mainOld2(String[] args) {
        String url = "http://www.youtube.com/watch?v=OfIfzVf8t6E";
        File path = new File("c:\\Denimar");

        try {
            final AtomicBoolean stop = new AtomicBoolean(false);
            URL web = new URL(url);
            VGetParser user = null;
            user = VGet.parser(web);    
            VideoInfo videoinfo = user.info(web);  
            VGet v = new VGet(videoinfo, path);
            VGetStatus notify = new VGetStatus(videoinfo);  
            v.extract(user, stop, notify);

            System.out.println("Title: " + videoinfo.getTitle());
            List<VideoFileInfo> list = videoinfo.getInfo();
            if (list != null) {
                for (VideoFileInfo d : list) {
                	if (d.getContentType().equals("video/mp4")) {
                		d.targetFile = new File(path, "newVideoName.mp4");
                	}
                }
            }

            v.download(user, stop, notify);
        } catch (DownloadInterruptedError e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	public static void mainOld(String[] args) {
		
        try {
        	File dir = new File("c:\\Denimar");
        	String miliseconds = String.valueOf(System.currentTimeMillis());
        	
            File outputFile = new File(dir, miliseconds + ".mp4");
            
            
            URL web = new URL("https://www.youtube.com/watch?v=F3J0iwwsq-w");
            VGetParser user = VGet.parser(web);
            VideoInfo videoinfo = user.info(web);
            
            VGet v = new VGet(videoinfo, dir);

            v.extract();
            
            AtomicBoolean confict = new AtomicBoolean(true);
            v.targetFile(videoinfo.getInfo().get(0), ".mp4", confict);
            v.setTarget(outputFile);
            
            DownloadYoutubeThread downloadYoutubeThread = new DownloadYoutubeThread(v);
            downloadYoutubeThread.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }		
		
	}
	
    /**
     * Downloads a file from a URL
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    public static void downloadFile(String fileURL, File targetFile) throws IOException {
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
