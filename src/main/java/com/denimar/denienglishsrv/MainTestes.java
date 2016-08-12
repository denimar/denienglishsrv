package com.denimar.denienglishsrv;

import java.io.File;
import java.net.URL;

import com.github.axet.vget.VGet;

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
		
        try {
            try {
                // ex: http://www.youtube.com/watch?v=Nj6PFaDmp6c
                String url = "http://www.youtube.com/watch?v=TK35Dh4CBfs";
                // ex: "/Users/axet/Downloads"
                String path = "c:\\Denimar";
                VGet v = new VGet(new URL(url), new File(path));
                v.download();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }		
		
	}
	
}
