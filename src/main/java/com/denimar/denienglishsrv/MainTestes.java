package com.denimar.denienglishsrv;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.denimar.denienglishsrv.AppManagedDownload.VGetStatus;
import com.denimar.denienglishsrv.dto.DownloadYoutubeVideoResponseDTO;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;
import com.github.axet.vget.VGet;
import com.github.axet.vget.info.VGetParser;
import com.github.axet.vget.info.VideoFileInfo;
import com.github.axet.vget.info.VideoInfo;
import com.github.axet.wget.info.ex.DownloadInterruptedError;

public class MainTestes {

	static class DownloadYoutubeThread extends Thread {
		VGet vGetDownload; 
		
		public DownloadYoutubeThread(VGet vGet) {
			this.vGetDownload = vGet;
		}
	 
		public void run() {
			this.vGetDownload.download();
		}
	}	
	
	public static void main(String[] args) {
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
	
}
