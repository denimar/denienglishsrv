package com.denimar.denienglishsrv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleDrive {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "status=ok&hl=en&allow_embed=0&ps=docs&partnerid=30&autoplay=0&docid=0B6WapjXIIlDGRU1xeTl5ODZmU28&abd=0&public=true&el=embed&title=2203.mp4&iurl=https%3A%2F%2Fdocs.google.com%2Fvt%3Fauthuser%3D0%26id%3D0B6WapjXIIlDGRU1xeTl5ODZmU28%26s%3DAMedNnoAAAAAV7HwBU164qOzLvh6Kzm1AtP0igstktsf&cc3_module=https%3A%2F%2Fs.ytimg.com%2Fyt%2Fswfbin%2Fsubtitles3_module.swf&ttsurl=https%3A%2F%2Fdocs.google.com%2Ftimedtext%3Fauthuser%3D0%26id%3D0B6WapjXIIlDGRU1xeTl5ODZmU28%26vid%3D317ca8ecd36058a6&reportabuseurl=https%3A%2F%2Fdocs.google.com%2Fabuse%3Fauthuser%3D0%26id%3D0B6WapjXIIlDGRU1xeTl5ODZmU28&token=1&plid=V0QUawIB69JIBg&fmt_stream_map=18%7Chttps%3A%2F%2Fr1---sn-u25-o8ue.googlevideo.com%2Fvideoplayback%3Frequiressl%3Dyes%26id%3D317ca8ecd36058a6%26itag%3D18%26source%3Dwebdrive%26ttl%3Dtransient%26app%3Dexplorer%26ip%3D177.200.192.66%26ipbits%3D0%26expire%3D1471286309%26sparams%3Drequiressl%252Cid%252Citag%252Csource%252Cttl%252Cip%252Cipbits%252Cexpire%26signature%3D6EE22D2B49BA579395E0C1FAEE4B699F29E15B2B.1107DB5F17107E9A03601D2D937FCD4D29953711%26key%3Dck2%26mm%3D31%26mn%3Dsn-u25-o8ue%26ms%3Dau%26mt%3D1471271331%26mv%3Dm%26pcm2cms%3Dyes%26pl%3D22%2C34%7Chttps%3A%2F%2Fr1---sn-u25-o8ue.googlevideo.com%2Fvideoplayback%3Frequiressl%3Dyes%26id%3D317ca8ecd36058a6%26itag%3D34%26source%3Dwebdrive%26ttl%3Dtransient%26app%3Dexplorer%26ip%3D177.200.192.66%26ipbits%3D0%26expire%3D1471286309%26sparams%3Drequiressl%252Cid%252Citag%252Csource%252Cttl%252Cip%252Cipbits%252Cexpire%26signature%3D39DE69DAE516845B134313311531CBC0475F1DC8.713A61E5D73969C95AFFF3681A5EF7F1EA429694%26key%3Dck2%26mm%3D31%26mn%3Dsn-u25-o8ue%26ms%3Dau%26mt%3D1471271331%26mv%3Dm%26pcm2cms%3Dyes%26pl%3D22%2C43%7Chttps%3A%2F%2Fr1---sn-u25-o8ue.googlevideo.com%2Fvideoplayback%3Frequiressl%3Dyes%26id%3D317ca8ecd36058a6%26itag%3D43%26source%3Dwebdrive%26ttl%3Dtransient%26app%3Dexplorer%26ip%3D177.200.192.66%26ipbits%3D0%26expire%3D1471286309%26sparams%3Drequiressl%252Cid%252Citag%252Csource%252Cttl%252Cip%252Cipbits%252Cexpire%26signature%3D87F6F51D7776C11AA55DE248E5BE0BC2A1897563.3155BAA3D4D73FF107AC9BE2CCE5845E71690A5C%26key%3Dck2%26mm%3D31%26mn%3Dsn-u25-o8ue%26ms%3Dau%26mt%3D1471271331%26mv%3Dm%26pcm2cms%3Dyes%26pl%3D22&fmt_list=18%2F640x360%2F9%2F0%2F115%2C34%2F640x360%2F9%2F0%2F115%2C43%2F640x360%2F99%2F0%2F0&url_encoded_fmt_stream_map=itag%3D18%26url%3Dhttps%253A%252F%252Fr1---sn-u25-o8ue.googlevideo.com%252Fvideoplayback%253Frequiressl%253Dyes%2526id%253D317ca8ecd36058a6%2526itag%253D18%2526source%253Dwebdrive%2526ttl%253Dtransient%2526app%253Dexplorer%2526ip%253D177.200.192.66%2526ipbits%253D0%2526expire%253D1471286309%2526sparams%253Drequiressl%252Cid%252Citag%252Csource%252Cttl%252Cip%252Cipbits%252Cexpire%2526signature%253D6EE22D2B49BA579395E0C1FAEE4B699F29E15B2B.1107DB5F17107E9A03601D2D937FCD4D29953711%2526key%253Dck2%2526mm%253D31%2526mn%253Dsn-u25-o8ue%2526ms%253Dau%2526mt%253D1471271331%2526mv%253Dm%2526pcm2cms%253Dyes%2526pl%253D22%26type%3Dvideo%252Fmp4%253B%2Bcodecs%253D%2522avc1.42001E%252C%2Bmp4a.40.2%2522%26quality%3Dmedium%2Citag%3D34%26url%3Dhttps%253A%252F%252Fr1---sn-u25-o8ue.googlevideo.com%252Fvideoplayback%253Frequiressl%253Dyes%2526id%253D317ca8ecd36058a6%2526itag%253D34%2526source%253Dwebdrive%2526ttl%253Dtransient%2526app%253Dexplorer%2526ip%253D177.200.192.66%2526ipbits%253D0%2526expire%253D1471286309%2526sparams%253Drequiressl%252Cid%252Citag%252Csource%252Cttl%252Cip%252Cipbits%252Cexpire%2526signature%253D39DE69DAE516845B134313311531CBC0475F1DC8.713A61E5D73969C95AFFF3681A5EF7F1EA429694%2526key%253Dck2%2526mm%253D31%2526mn%253Dsn-u25-o8ue%2526ms%253Dau%2526mt%253D1471271331%2526mv%253Dm%2526pcm2cms%253Dyes%2526pl%253D22%26type%3Dvideo%252Fx-flv%26quality%3Dmedium%2Citag%3D43%26url%3Dhttps%253A%252F%252Fr1---sn-u25-o8ue.googlevideo.com%252Fvideoplayback%253Frequiressl%253Dyes%2526id%253D317ca8ecd36058a6%2526itag%253D43%2526source%253Dwebdrive%2526ttl%253Dtransient%2526app%253Dexplorer%2526ip%253D177.200.192.66%2526ipbits%253D0%2526expire%253D1471286309%2526sparams%253Drequiressl%252Cid%252Citag%252Csource%252Cttl%252Cip%252Cipbits%252Cexpire%2526signature%253D87F6F51D7776C11AA55DE248E5BE0BC2A1897563.3155BAA3D4D73FF107AC9BE2CCE5845E71690A5C%2526key%253Dck2%2526mm%253D31%2526mn%253Dsn-u25-o8ue%2526ms%253Dau%2526mt%253D1471271331%2526mv%253Dm%2526pcm2cms%253Dyes%2526pl%253D22%26type%3Dvideo%252Fwebm%26quality%3Dmedium&timestamp=1471271909695&length_seconds=596";
        System.out.println(new String(str.getBytes("UTF-8"),"ASCII"));
	}
	
	public static void mainOld(String[] args) {
        String fileURL = "https://googledrive.com/host/0B6WapjXIIlDGRU1xeTl5ODZmU28";
        File targetFile = new File("C:/Denimar/Movie.mp4"); 
        try {
            downloadFile(fileURL, targetFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }		
	}
	
	public static void sendFileToGoogleDrive(File fileToSend) {
		
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
